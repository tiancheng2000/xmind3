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
package org.xmind.ui.wizards;

import org.xmind.core.IRelationship;
import org.xmind.core.IRelationshipEnd;

public class RelationshipDescription {

    public final IRelationship relationship;

    public final IRelationshipEnd source;

    public final IRelationshipEnd target;

    public final String description;

    public RelationshipDescription(IRelationship relationship,
            IRelationshipEnd source, IRelationshipEnd target, String description) {
        this.relationship = relationship;
        this.source = source;
        this.target = target;
        this.description = description;
    }

}