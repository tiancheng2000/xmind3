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
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.xmind.gef.draw2d.geometry.PrecisionPoint;
import org.xmind.gef.draw2d.graphics.Path;
import org.xmind.ui.decorations.AbstractTopicDecoration;

public class UnderlineTopicDecoration extends AbstractTopicDecoration {

    private static PrecisionPoint P = new PrecisionPoint();

    public UnderlineTopicDecoration() {
        super();
    }

    public UnderlineTopicDecoration(String id) {
        super(id);
    }

    private PrecisionPoint getLeftAnchor(Rectangle rect, double expansion) {
        return P.setLocation(rect.x - expansion, rect.y + rect.height + 1);
    }

    private PrecisionPoint getRightAnchor(Rectangle rect, double expansion) {
        return P.setLocation(rect.x + rect.width + expansion, rect.y
                + rect.height + 1);
    }

    protected void sketch(IFigure figure, Path shape, Rectangle box, int purpose) {
        if (purpose == OUTLINE) {
            shape.moveTo(getLeftAnchor(box, 0));
            shape.lineTo(getRightAnchor(box, 0));
        } else {
            shape.addRectangle(box);
        }
    }

    public PrecisionPoint getAnchorLocation(IFigure figure, int orientation,
            double expansion) {
        if (orientation == PositionConstants.EAST) {
            return getRightAnchor(getOutlineBox(figure), expansion).getCopy();
        } else if (orientation == PositionConstants.WEST) {
            return getLeftAnchor(getOutlineBox(figure), expansion).getCopy();
        }
        return super.getAnchorLocation(figure, orientation, expansion);
    }

}