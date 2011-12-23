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
package org.xmind.gef.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.xmind.gef.Disposable;

/**
 * @author Brian Sun
 */
public abstract class CommandStackBase extends Disposable implements
        ICommandStack {

    public static int DEFAULT_UNDO_LIMIT = 20;

    private List<ICommandStackListener> commandStackListeners = null;

    private int undoLimit;

    /**
     * @param undoLimit
     */
    public CommandStackBase(int undoLimit) {
        this.undoLimit = undoLimit;
    }

    /**
     * 
     */
    public CommandStackBase() {
        this(DEFAULT_UNDO_LIMIT);
    }

    /**
     * @see org.xmind.gef.command.ICommandStack#addCSListener(org.xmind.gef.command.ICommandStackListener)
     */
    public void addCSListener(ICommandStackListener listener) {
        if (commandStackListeners == null)
            commandStackListeners = new ArrayList<ICommandStackListener>();
        commandStackListeners.add(listener);
    }

    /**
     * @see org.xmind.gef.command.ICommandStack#removeCSListener(org.xmind.gef.command.ICommandStackListener)
     */
    public void removeCSListener(ICommandStackListener listener) {
        if (commandStackListeners == null)
            return;
        commandStackListeners.remove(listener);
    }

    protected void fireEvent(int status) {
        if (commandStackListeners == null)
            return;
        fireEvent(new CommandStackEvent(this, status));
    }

    protected void fireEvent(Command cmd, int status) {
        if (commandStackListeners == null)
            return;
        fireEvent(new CommandStackEvent(this, cmd, status));
    }

    protected void fireEvent(CommandStackEvent event) {
        if (commandStackListeners == null)
            return;
        for (Object listener : commandStackListeners.toArray()) {
            ((ICommandStackListener) listener).handleCommandStackEvent(event);
        }
    }

    /**
     * @see org.xmind.framework.Disposable#removeFromLayer(IFigure)
     */
    @Override
    public void dispose() {
        if (commandStackListeners != null) {
            commandStackListeners.clear();
            commandStackListeners = null;
        }
        clear();
        super.dispose();
    }

    /**
     * @see org.xmind.gef.command.ICommandStack#getUndoLimit()
     */
    public int getUndoLimit() {
        return undoLimit;
    }

    /**
     * @see org.xmind.gef.command.ICommandStack#setUndoLimit(int)
     */
    public void setUndoLimit(int undoLimit) {
        this.undoLimit = undoLimit;
    }

    /**
     * @see org.xmind.gef.command.ICommandStack#getRepeatLabel()
     * @deprecated
     */
    public String getRepeatLabel() {
        return getUndoLabel();
    }

    /**
     * @deprecated
     */
    public boolean canRepeat() {
        return false;
    }

    /**
     * @deprecated
     */
    public void repeat() {
    }

    public void markSaved() {
    }

    public boolean isDirty() {
        return canUndo();
    }
}