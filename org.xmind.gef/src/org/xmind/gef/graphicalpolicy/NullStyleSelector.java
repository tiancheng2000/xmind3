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
package org.xmind.gef.graphicalpolicy;

import org.xmind.gef.part.IGraphicalPart;

/**
 * @author Frank Shaka
 */
public class NullStyleSelector extends AbstractStyleSelector {

    private static final NullStyleSelector instance = new NullStyleSelector();

    private NullStyleSelector() {
    }

    public String getAutoValue(IGraphicalPart part, String key,
            IStyleValueProvider defaultValueProvider) {
        return null;
    }

    public String getUserValue(IGraphicalPart part, String key) {
        return null;
    }

    public static NullStyleSelector getInstance() {
        return instance;
    }

}