/*
 * Created on Aug 31, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.flexdock.docking.drag.outline.win32;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.flexdock.docking.drag.outline.AbstractRubberBand;
import org.flexdock.util.ResourceManager;

/**
 * @author cb8167
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Win32RubberBand extends AbstractRubberBand {
	private static final String NATIVE_RESOURCE = "org/flexdock/docking/drag/outline/win32/RubberBand.dll";
	private static final String NATIVE_LIB = "RubberBand";
	
	private static final Win32RubberBand SINGLETON = new Win32RubberBand();
	private Rectangle currentRect;
	
	private native void drawRectangle(int x, int y, int width, int height);
	private native void clearRectangle(int x, int y, int width, int height);
	private native void cleanup();
	
	static {
		ResourceManager.loadLibrary(NATIVE_LIB, NATIVE_RESOURCE);
	}
	
	private Win32RubberBand() {
		
	}
	
	public void paint(Graphics g, Rectangle r) {
		clear();
		currentRect = r;
		draw(currentRect, true);
	}
	
	public void clear() {
		draw(currentRect, false);
		currentRect = null;
	}
	
	private void draw(Rectangle r, boolean xor) {
		if(r==null)
			return;
		
		if(xor)
			drawRectangle(r.x, r.y, r.width, r.height);
		else
			clearRectangle(r.x, r.y, r.width, r.height);
	}
	
	public static AbstractRubberBand getInstance() {
		return SINGLETON;
	}
}