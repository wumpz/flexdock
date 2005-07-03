/* 
 * Copyright (c) 2005 FlexDock Development Team. All rights reserved. 
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of 
 * this software and associated documentation files (the "Software"), to deal in the 
 * Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all 
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE.
 */
package org.flexdock.perspective.persist.xml;

import javax.swing.tree.MutableTreeNode;

import org.flexdock.docking.state.LayoutNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created on 2005-06-27
 * 
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 * @version $Id: AbstractLayoutNodeSerializer.java,v 1.4 2005-07-03 13:11:56 winnetou25 Exp $
 */
public abstract class AbstractLayoutNodeSerializer implements ISerializer {
    
    public Element serialize(Document document, Object object) {
        LayoutNode layoutNode = (LayoutNode) object;

        Element layoutNodeElement = getElement(document, object);
        
        ISerializer layoutNodeSerializer = SerializerRegistry.getSerializer(LayoutNode.class);
        for (int i=0; i<layoutNode.getChildCount(); i++) {
            MutableTreeNode childTreeNode = (MutableTreeNode) layoutNode.getChildAt(i);
            if (childTreeNode.isLeaf()) {
                Element element = layoutNodeSerializer.serialize(document, childTreeNode);
                layoutNodeElement.appendChild(element);
            } else {
                Element element = layoutNodeSerializer.serialize(document, childTreeNode); //recursion
                layoutNodeElement.appendChild(element);
            }
        }
        
        return layoutNodeElement;
    }
    
    protected abstract Element getElement(Document document, Object o);
    
    public Object deserialize(Element element, DeserializationStack deserializationStack) {

//        ISerializer layoutNodeSerializer = SerializerRegistry.getSerializer(LayoutNode.class);
//
//        LayoutNode layoutNode = (LayoutNode) layoutNodeSerializer.deserialize(element, deserializationStack);
        
        return null;
        
    }
    
}
