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
package org.xmind.gef.draw2d;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;
import org.xmind.gef.draw2d.geometry.PrecisionDimension;
import org.xmind.gef.draw2d.geometry.PrecisionRectangle;

/**
 * @author Frank Shaka
 */
public class PathFigure extends Shape {

    private static final float[] _bounds = new float[4];

    private Path path = null;
    private boolean outline = true;
    private boolean fill = true;
    private int tolerance = 0;

//    private int alpha = 0xff;
//
//    public int getAlpha() {
//        return alpha;
//    }
//
//    public void setAlpha(int alpha) {
//        if (alpha == this.alpha)
//            return;
//        this.alpha = alpha;
//        repaint();
//    }
//  @Override
//  public void paintFigure(Graphics graphics) {
//      graphics.setAlpha(alpha);
//      super.paintFigure(graphics);
//  }

    /**
     * 
     */
    public PathFigure() {
    }

    /**
     * @return the path
     */
    public Path getPath() {
        return path;
    }

    /**
     * <b>NOTE:</b> It's client's responsibility to dispose the path.
     * 
     * @param path
     *            the path to set
     */
    public void setPath(Path path) {
        if (this.path == path || (this.path != null && this.path.equals(path)))
            return;
        this.path = path;
        revalidate();
//        repaint();
//        bounds = null;
    }

    /**
     * @return the tolerance
     */
    public int getTolerance() {
        return tolerance;
    }

    /**
     * @param tolerance
     *            the tolerance to set
     */
    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    /**
     * @see org.eclipse.draw2d.Shape#fillShape(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void fillShape(Graphics graphics) {
        if (path != null)
            graphics.fillPath(path);
    }

    /**
     * @see org.eclipse.draw2d.Shape#outlineShape(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void outlineShape(Graphics graphics) {
        if (path != null)
            graphics.drawPath(path);
    }

    /**
     * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
     */
    @Override
    public Dimension getPreferredSize(int wHint, int hHint) {
        if (path != null) {
            path.getBounds(_bounds);
            PrecisionDimension pSize = new PrecisionDimension(_bounds[2],
                    _bounds[3]);
            double halfLine = getLineWidth() * 0.5d + 1.0;
            pSize.expand(halfLine, halfLine);
            return pSize.toDraw2DDimension();
        }
        return super.getPreferredSize(wHint, hHint);
    }

//    public Rectangle getBounds() {
//        if ( bounds == null ) {
//            bounds = getPreferredBounds();
//        }
//        return bounds;
//    }

    public Rectangle getPreferredBounds() {
        if (path == null)
            return new Rectangle();
        path.getBounds(_bounds);
        PrecisionRectangle pRect = new PrecisionRectangle(_bounds[0],
                _bounds[1], _bounds[2], _bounds[3]);
        double halfLine = getLineWidth() * 0.5d;
        pRect.expand(halfLine, halfLine).resize(1.0, 1.0);
        return pRect.toDraw2DRectangle();
    }

    /**
     * @see org.eclipse.draw2d.Figure#containsPoint(int, int)
     */
    @Override
    public boolean containsPoint(int x, int y) {
        if (!super.containsPoint(x, y) || path == null
                || (!hasOutline() && !hasFill()))
            return false;
        GC gc = new GC(Display.getCurrent());
        gc.setLineWidth(getLineWidth() + tolerance);
        gc.setLineStyle(getLineStyle());
        boolean b = path.contains(x, y, gc, !hasFill());
        gc.dispose();
        return b;
    }

    /**
     * @see org.eclipse.draw2d.Shape#setOutline(boolean)
     */
    @Override
    public void setOutline(boolean b) {
        super.setOutline(b);
        this.outline = b;
    }

    /**
     * @see org.eclipse.draw2d.Shape#setFill(boolean)
     */
    @Override
    public void setFill(boolean b) {
        super.setFill(b);
        this.fill = b;
    }

    public boolean hasOutline() {
        return outline;
    }

    public boolean hasFill() {
        return fill;
    }

}