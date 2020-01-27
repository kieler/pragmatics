/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018-2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.gson_utils

import com.google.gson.GsonBuilder
import de.cau.cs.kieler.klighd.SynthesisOption
import de.cau.cs.kieler.klighd.lsp.interactive.layered.DeleteLayerConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.layered.DeletePositionConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.layered.DeleteStaticConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.layered.SetLayerConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.layered.SetPositionConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.layered.SetStaticConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.rectpack.RectPackDeletePositionConstraintAction
import de.cau.cs.kieler.klighd.lsp.interactive.rectpack.RectPackSetPositionConstraintAction
import de.cau.cs.kieler.klighd.lsp.model.CheckedImagesAction
import de.cau.cs.kieler.klighd.lsp.model.ComputedTextBoundsAction
import de.cau.cs.kieler.klighd.lsp.model.PerformActionAction
import de.cau.cs.kieler.klighd.lsp.model.RefreshLayoutAction
import de.cau.cs.kieler.klighd.lsp.model.SetSynthesisAction
import java.awt.geom.Point2D
import org.eclipse.emf.ecore.EObject
import org.eclipse.sprotty.server.json.ActionTypeAdapter
import de.cau.cs.kieler.klighd.lsp.interactive.rectpack.SetAspectRatioAction

/**
 * Static util class to configure needed gson type adapters for KGraph serialization.
 * 
 * @author nre
 */
public class KGraphTypeAdapterUtil {
    public def static GsonBuilder configureGson(GsonBuilder gsonBuilder) {
        gsonBuilder
        .registerTypeAdapterFactory(
            new ActionTypeAdapter.Factory => [
                addActionKind(CheckedImagesAction.KIND, CheckedImagesAction)
                addActionKind(ComputedTextBoundsAction.KIND, ComputedTextBoundsAction)
                addActionKind(PerformActionAction.KIND, PerformActionAction)
                addActionKind(SetSynthesisAction.KIND, SetSynthesisAction)
                addActionKind(RefreshLayoutAction.KIND, RefreshLayoutAction)
                addActionKind(SetStaticConstraintAction.KIND, SetStaticConstraintAction)
                addActionKind(SetPositionConstraintAction.KIND, SetPositionConstraintAction)
                addActionKind(SetLayerConstraintAction.KIND, SetLayerConstraintAction)
                addActionKind(DeleteStaticConstraintAction.KIND, DeleteStaticConstraintAction)
                addActionKind(DeletePositionConstraintAction.KIND, DeletePositionConstraintAction)
                addActionKind(DeleteLayerConstraintAction.KIND, DeleteLayerConstraintAction)
                addActionKind(RectPackSetPositionConstraintAction.KIND, RectPackSetPositionConstraintAction)
                addActionKind(RectPackDeletePositionConstraintAction.KIND, RectPackDeletePositionConstraintAction)
                addActionKind(SetAspectRatioAction.KIND, SetAspectRatioAction)
            ]
        )
        .registerTypeAdapter(Point2D, new Point2DTypeAdapter)
        .registerTypeHierarchyAdapter(EObject, new EObjectSerializer)
        .registerTypeAdapter(SynthesisOption, new SynthesisOptionSerializer)
    }
}