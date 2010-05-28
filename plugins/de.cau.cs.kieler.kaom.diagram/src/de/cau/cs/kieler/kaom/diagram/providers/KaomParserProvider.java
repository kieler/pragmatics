package de.cau.cs.kieler.kaom.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.kaom.diagram.edit.parts.ActorName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.ActorNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.StateName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.StateNameEditPart;
import de.cau.cs.kieler.kaom.diagram.parsers.MessageFormatParser;
import de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry;

/**
 * @generated
 */
public class KaomParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser actorName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getActorName_5005Parser() {
		if (actorName_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { KaomPackage.eINSTANCE
					.getNamedObject_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			actorName_5005Parser = parser;
		}
		return actorName_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser stateName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getStateName_5006Parser() {
		if (stateName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { KaomPackage.eINSTANCE
					.getNamedObject_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			stateName_5006Parser = parser;
		}
		return stateName_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser relationName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getRelationName_5007Parser() {
		if (relationName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { KaomPackage.eINSTANCE
					.getNamedObject_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			relationName_5007Parser = parser;
		}
		return relationName_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser portName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getPortName_5001Parser() {
		if (portName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { KaomPackage.eINSTANCE
					.getNamedObject_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			portName_5001Parser = parser;
		}
		return portName_5001Parser;
	}

	/**
	 * @generated
	 */
	private IParser actorName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getActorName_5004Parser() {
		if (actorName_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { KaomPackage.eINSTANCE
					.getNamedObject_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			actorName_5004Parser = parser;
		}
		return actorName_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser stateName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getStateName_5003Parser() {
		if (stateName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { KaomPackage.eINSTANCE
					.getNamedObject_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			stateName_5003Parser = parser;
		}
		return stateName_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser relationName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getRelationName_5002Parser() {
		if (relationName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { KaomPackage.eINSTANCE
					.getNamedObject_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			relationName_5002Parser = parser;
		}
		return relationName_5002Parser;
	}

	/**
	 * @generated
	 */
	private IParser linkName_6001Parser;

	/**
	 * @generated
	 */
	private IParser getLinkName_6001Parser() {
		if (linkName_6001Parser == null) {
			EAttribute[] features = new EAttribute[] { KaomPackage.eINSTANCE
					.getNamedObject_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			linkName_6001Parser = parser;
		}
		return linkName_6001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ActorNameEditPart.VISUAL_ID:
			return getActorName_5005Parser();
		case StateNameEditPart.VISUAL_ID:
			return getStateName_5006Parser();
		case RelationNameEditPart.VISUAL_ID:
			return getRelationName_5007Parser();
		case PortNameEditPart.VISUAL_ID:
			return getPortName_5001Parser();
		case ActorName2EditPart.VISUAL_ID:
			return getActorName_5004Parser();
		case StateName2EditPart.VISUAL_ID:
			return getStateName_5003Parser();
		case RelationName2EditPart.VISUAL_ID:
			return getRelationName_5002Parser();
		case LinkNameEditPart.VISUAL_ID:
			return getLinkName_6001Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(KaomVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(KaomVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (KaomElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
