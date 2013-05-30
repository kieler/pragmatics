package de.cau.cs.kieler.klighd.kdiagram.jvmmodel

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensionsPlugin
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping
import de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis
import javax.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.common.types.JvmAnnotationType
import org.eclipse.xtext.common.types.JvmFormalParameter
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.common.types.TypesFactory
import org.eclipse.xtext.common.types.util.TypeReferences
import org.eclipse.xtext.xbase.compiler.XbaseCompiler
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import org.eclipse.xtext.xbase.typing.XbaseTypeProvider
import de.cau.cs.kieler.core.krendering.KRendering
import com.google.common.collect.Iterables
import com.google.common.base.Function
import java.util.List
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference
import org.eclipse.xtext.xbase.lib.Pair
import com.google.common.collect.ImmutableList
import java.util.Iterator
import com.google.common.collect.Iterators



/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
class KDiagramJvmModelInferrer extends AbstractModelInferrer {

    /**
     * convenience API to build and initialize JVM types and their members.
     */
	@Inject
	extension JvmTypesBuilder

	/**
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
        acceptor.accept(synthesis.toClass(synthesis.name) => [
            it.packageName = synthesis.packageName;
        ]).initializeLater([
            it.initialize(synthesis)
        ]);
   	}
   	
    @Inject
    extension XbaseCompiler
    
    @Inject
    extension TypesFactory
    
    @Inject
    extension TypeReferences
    
    @Inject
    extension XbaseTypeProvider

    
   	def initialize(JvmGenericType clazz, DiagramSynthesis synthesis) {
        clazz.superTypes += synthesis.newTypeRef(typeof(AbstractDiagramSynthesis), synthesis?.mapping?.type)
        clazz.addKRenderingExtensions(synthesis);
        
        clazz.members += synthesis.mapping.toMethod("transform", synthesis.newTypeRef(typeof(KNode))) [ method |
            method.parameters += synthesis?.mapping?.toParameter(synthesis?.mapping?.name, synthesis?.mapping?.type);
            
            val List<Pair<JvmTypeReference, List<JvmTypeReference>>> inputTypes = synthesis.mapping.nodeMappings.map[
                val type = it.elements.type;
                Pair::of(type, (if (type instanceof JvmParameterizedTypeReference)
                                ImmutableList::copyOf((type as JvmParameterizedTypeReference).getArguments())
                                else null) as List<JvmTypeReference>
                )
            ].toList;
            method.body = [
                append('''final KNode root = kNodeExtensions.createNode();''').newLine(); //.newLine();
                val inputTypesIt = inputTypes.iterator();
                for (NodeMapping nm : synthesis.mapping.nodeMappings) {
                    val pair = inputTypesIt.next;
                    val inputType = pair.key;

                    val collectionsHelper = switch(null) {
                        case inputType?.isInstanceOf(typeof(Iterable)): clazz?.newTypeRef(typeof(Iterables))
                        case inputType?.isInstanceOf(typeof(Iterator)): clazz?.newTypeRef(typeof(Iterators))
                        default: null
                    }?.type;
                    
                    if (collectionsHelper != null) {
                        // the following instruction creates required Java statements
                        //  for evaluating the node elements expression
                        nm.elements.toJavaStatement(it, true);
                        it.newLine().newLine();
                        
                        it.append(collectionsHelper).append('''.addAll(root.getChildren(),''')
                          .increaseIndentation().newLine();
                          
                        it.append(collectionsHelper).append(".transform(");
                        // this statement appends the expression
                        //  that references the above created statement
                        nm.elements.toJavaExpression(it);
                        
                        it.append(''',''').increaseIndentation().newLine();
                        
                        val actualType = pair.value.head.type
                        
                        // "new Function<@actualType@, KNode>() {"
                        it.append("new ").append(typeof(Function).findDeclaredType(synthesis)).append("<")
                          .append(actualType).append(", ").append(method.returnType.type).append('''>() {''')
                          .increaseIndentation().newLine;
                        
                        it.append('''public KNode apply(final «actualType.simpleName» input) {''').increaseIndentation().newLine;
                        it.append('''return «nm.name»(input);''').decreaseIndentation().newLine;
                        it.append('''}''').decreaseIndentation().newLine;
                        
                        it.decreaseIndentation.append('''}));''').decreaseIndentation().newLine();
                    } else {
                        // the following instruction creates required Java statements
                        //  for evaluating the node elements expression
                        nm.elements.toJavaStatement(it, true);
                        it.newLine();
                        
                        it.append('''root.getChildren().add(«nm.name»(''');                    
                        // this statement appends the expression
                        //  that references the above created statement
                        nm.elements.toJavaExpression(it);
                        
                        it.append('''));''').newLine();
                    }
                }
                it.newLine();
                it.append('''return root;''');
            ];
        ];
        
        for (NodeMapping mapping : (synthesis?.mapping?.nodeMappings as List<NodeMapping>)?:emptyList()) {
            clazz.members += mapping.createMethod(mapping.name, typeof(KNode)) => [
                val expressionType = mapping?.elements?.type;
                val paramType = if (expressionType.isInstanceOf(typeof(Iterable)) || expressionType.isInstanceOf(typeof(Iterator))) 
                                    expressionType.getArgument(0)
                                else expressionType
                it.parameters += mapping.createParameter("input", paramType);
                it.body = [
                    append('''final KNode node = kNodeExtensions.createNode(input);''').newLine();
                    append(clazz.newTypeRef(typeof(KRendering)).type);
                    append(''' r = kRenderingExtensions.add«mapping.figureType.simpleName.substring(1)»(node);''').newLine();
                    append('''node.getData().add(r);''').newLine();
                    append('''return node;''');
                ];
            ];
        }
   	}

    private val EXTENSIONS = newArrayList("KNode", "KEdge", "KPort", "KLabel", "KRendering",
        "KContainerRendering", "KPolyline", "KColor");     

   	def addKRenderingExtensions(JvmGenericType type, EObject helper) {
   	    val annotationType = findDeclaredType(typeof(Inject), helper) as JvmAnnotationType
   	    
   	    EXTENSIONS.forEach[ name |
   	        type.members += createJvmField() => [
                it.type = helper.newTypeRef(KRenderingExtensionsPlugin::PLUGIN_ID +"." + name + "Extensions");
       	        it.simpleName = name.toFirstLower + "Extensions";
       	        it.visibility = JvmVisibility::PRIVATE;
                it.annotations += createJvmAnnotationReference() => [
                    it.annotation = annotationType;
                ];
   	        ];
   	    ];
   	}
   	
   	def JvmOperation createMethod(EObject helper, String name, Class<?> returnType) {
   	    return createJvmOperation() => [
            it.visibility = JvmVisibility::PUBLIC;   	        
   	        it.returnType = helper.newTypeRef(returnType);
   	        it.simpleName = name;
   	    ];
   	}
   	
    def JvmFormalParameter createParameter(EObject helper, String name, Class<?> type) {
        return createJvmFormalParameter() => [
            it.name = name;
            it.parameterType = helper.newTypeRef(type);
        ];
    }
    
    def JvmFormalParameter createParameter(EObject helper, String name, JvmTypeReference type) {
        return createJvmFormalParameter() => [
            it.name = name;
            it.parameterType = type.cloneWithProxies;
        ];
    }
    
    def JvmFormalParameter createParameter(String name, JvmTypeReference type) {
        return createJvmFormalParameter() => [
            it.name = name;
            it.parameterType = type;
        ];
    }
}