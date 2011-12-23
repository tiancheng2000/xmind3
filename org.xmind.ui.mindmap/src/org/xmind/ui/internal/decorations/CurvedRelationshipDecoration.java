/* ******************************************************************************
 * Copyright (c) 2006-2010 XMind Ltd. and others.
 * 
 * This file is a part of XMind 3. XMind releases 3 and
 * above are dual-licensed under the Eclipse Public License (EPL),
 * which is available at http://www.eclipse.org/legal/epl-v10.html
 * and the GNU Lesser General Public License (LGPL), 
 * which is available at http://www.gnu.org/licenses/lgpl.html
 * See http://www.xmind.net/license.html for details.
 * 
 * Contributors:
 *     XMind Ltd. - initial API and implementation
 *******************************************************************************/
package org.xmind.ui.internal.decorations;

import org.eclipse.draw2d.IFigure;
import org.xmind.gef.draw2d.geometry.PrecisionPoint;
import org.xmind.gef.draw2d.graphics.Path;
import org.xmind.ui.decorations.AbstractRelationshipDecoration;

public class CurvedRelationshipDecoration extends
        AbstractRelationshipDecoration {

    public CurvedRelationshipDecoration() {
    }

    public CurvedRelationshipDecoration(String id) {
        super(id);
    }

    protected void route(IFigure figure, Path shape) {
        PrecisionPoint sp = getSourcePosition(figure);
        PrecisionPoint tp = getTargetPosition(figure);
        PrecisionPoint cp1 = getSourceControlPoint(figure);
        PrecisionPoint cp2 = getTargetControlPoint(figure);
        shape.moveTo(sp);
        shape.cubicTo(cp1, cp2, tp);
    }

    protected void calcTitlePosition(IFigure figure, PrecisionPoint titlePos,
            PrecisionPoint sourcePos, PrecisionPoint targetPos,
            PrecisionPoint sourceCP, PrecisionPoint targetCP) {

        double x = 0.125 * (sourcePos.x) + 3 * 0.125 * (sourceCP.x) + 3 * 0.125
                * (targetCP.x) + 0.125 * (targetPos.x);
        double y = 0.125 * (sourcePos.y) + 3 * 0.125 * (sourceCP.y) + 3 * 0.125
                * (targetCP.y) + 0.125 * (targetPos.y);
        titlePos.setLocation(x, y);
    }

}