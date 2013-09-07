package de.cau.cs.kieler.klighd.kdiagram.jvmmodel

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensionsPlugin
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping
import de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis
import java.util.List
import javax.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping

/**
 * <p>Infers a JVM model from the given KDiagram instance.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
@SuppressWarnings("restriction")
class KDiagramJvmModelInferrer extends AbstractModelInferrer {

     /**
     * convenience API to build and initialize JVM types and their members.
     */
	@Inject
	extension JvmTypesBuilder
	
     @Inject
     extension CodeGenHelper

    // @Inject
    // extension XbaseCompiler
    
    @Inject
    extension NodeMappingTemplate
    
	/**
	 * chsch: The following text was auto-generated by Xtext 2.3!<br>
	 * <br> 
	 * The dispatch method {@code infer} is called for each instance of the
	 * given element's type that is contained in a resource.
	 * 
	 * @param element
	 *            the model to create one or more
	 *            {@link org.eclipse.xtext.common.types.JvmDeclaredType declared
	 *            types} from.
	 * @param acceptor
	 *            each created
	 *            {@link org.eclipse.xtext.common.types.JvmDeclaredType type}
	 *            without a container should be passed to the acceptor in order
	 *            get attached to the current resource. The acceptor's
	 *            {@link IJvmDeclaredTypeAcceptor#accept(org.eclipse.xtext.common.types.JvmDeclaredType)
	 *            accept(..)} method takes the constructed empty type for the
	 *            pre-indexing phase. This one is further initialized in the
	 *            indexing phase using the closure you pass to the returned
	 *            {@link org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor.IPostIndexingInitializing#initializeLater(org.eclipse.xtext.xbase.lib.Procedures.Procedure1)
	 *            initializeLater(..)}.
	 * @param isPreIndexingPhase
	 *            whether the method is called in a pre-indexing phase, i.e.
	 *            when the global index is not yet fully updated. You must not
	 *            rely on linking using the index if isPreIndexingPhase is
	 *            <code>true</code>.
	 */
   	def dispatch void infer(DiagramSynthesis synthesis, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		// Here you explain how your model is mapped to Java elements, by writing the actual translation code.
   		
   		// This implementation basically follows the inferrers in the examples on http://www.eclipse.org/Xtext/7languagesDoc.html
        acceptor.accept(synthesis.toClass(synthesis.name) [
            it.packageName = synthesis.packageName;
        ]).initializeLater [
            it.initialize(synthesis)
        ];
   	}
   	
   	/**
   	 * Creates the fields and methods of the class to be generated based on the information given in synthesis.
   	 */
   	def initialize(JvmGenericType clazz, DiagramSynthesis synthesis) {
   	    // all generated classes must be subtypes of AbstractDiagramSynthesis, so ...
        clazz.superTypes += synthesis.newTypeRef(typeof(AbstractDiagramSynthesis), synthesis?.mapping?.type)
        
        // always instantiate the K...Extension classes in order to access the extension methods
        clazz.addKRenderingExtensions(synthesis);

        // add the root method of the synthesis (required by AbstractDiagramSynthesis)
        clazz.members += synthesis.mapping.toMethod("transform", synthesis.newTypeRef(typeof(KNode))) [ method |
            method.parameters += synthesis?.mapping?.toParameter(synthesis?.mapping?.name, synthesis?.mapping?.type);
            
            method.body = [
                it + '''final KNode root = kNodeExtensions.createNode();''' + newline + newline;

                (synthesis?.mapping?.nodeMappings?:emptyList).forEach[ nm |
                    // with this line the call of the method related to the current node mapping is added
                    //  note that this call relies on the results of a call of the related "...Expr" method
                    it + '''«nm.name»(«nm.name»Expr(«synthesis.mapping.name»), root);''' + newline
                    
                    // In contrast to synthesizing and calling that extra "...Expr" method I would like to
                    //  realize it the following way. Unfortunately, the new (v2.4) basic requirement stating
                    //  that each XExpression must be attached to the JvmModel is (still) violated by the
                    //  following solution (requires an 'extension field' of type XbaseCompiler):
                    //
                    // nm.elements.toJavaStatement(it, true);
                    // it.newLine;
                    // it.append('''«nm.name»(''')
                    // nm.elements.toJavaExpression(it);
                    // it.append(''', root);''').newLine
                ];
                
                it + newline
                + '''return root;''';
            ];
        ];

        // for each node mapping generate a related method realizing that mapping as well as a
        //  helper method realizing the elements expression;
        //  see above for the necessity of that helper method
        // for reasons of clarity and comprehensibility the code template of the first method is
        //  outsourced to the class NodeMappingTemplate 
        for (NodeMapping mapping : (synthesis?.mapping?.nodeMappings as List<NodeMapping>)?:emptyList()) {
            val expressionType = mapping.elements.inferredType;

            clazz.members += mapping.toMethod(mapping.name, synthesis.newTypeRef(Void.TYPE)) [
                it.visibility = JvmVisibility.PRIVATE;
                it.parameters += toParameter("elements", expressionType);
                it.parameters += toParameter("parent", synthesis.newTypeRef(typeof(KNode)));
                it.body = mapping.nodeMapping(mapping.figureType.simpleName.substring(1));
            ];

            clazz.members += mapping.toMethod(mapping.name + "Expr", expressionType) [
                it.visibility = JvmVisibility.PRIVATE;
                it.parameters += synthesis.mapping.toParameter(synthesis.mapping.name, synthesis.mapping.type);
                it.body = mapping.elements
            ]
        }
        
        for (PortMapping mapping : (synthesis?.mapping?.portMappings as List<PortMapping>)?:emptyList()) {
            val expressionType = mapping.elements.inferredType;

            clazz.members += mapping.toMethod(mapping.name, synthesis.newTypeRef(Void.TYPE)) [
                it.visibility = JvmVisibility.PRIVATE;
                it.parameters += toParameter("elements", expressionType);
                it.parameters += toParameter("parent", synthesis.newTypeRef(typeof(KNode)));
                it.body = mapping.nodeMapping(mapping.figureType.simpleName.substring(1));
            ];

            clazz.members += mapping.toMethod(mapping.name + "Expr", expressionType) [
                it.visibility = JvmVisibility.PRIVATE;
                it.parameters += synthesis.mapping.toParameter(synthesis.mapping.name, synthesis.mapping.type);
                it.parameters += synthesis.mapping.toParameter(mapping.nodeElementName, mapping.nodeElementType);
                it.body = mapping.elements
            ]
        }
   	}

    private val EXTENSIONS = newArrayList("KNode", "KEdge", "KPort", "KLabel", "KRendering",
        "KContainerRendering", "KPolyline", "KColor");     

   	def addKRenderingExtensions(JvmGenericType type, EObject helper) {
   	    EXTENSIONS.forEach[ name |
   	        type.members += helper.toField(name.toFirstLower + "Extensions", 
                helper.newTypeRef(KRenderingExtensionsPlugin::PLUGIN_ID +"." + name + "Extensions")) [
       	        it.visibility = JvmVisibility::PRIVATE;
       	        it.annotations += helper.toAnnotation(typeof(Extension));
       	        it.annotations += helper.toAnnotation(typeof(Inject));
   	        ];
   	    ];
   	}
   	
}