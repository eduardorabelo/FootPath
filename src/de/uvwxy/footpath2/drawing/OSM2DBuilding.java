package de.uvwxy.footpath2.drawing;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import de.uvwxy.footpath2.map.GraphEdge;
import de.uvwxy.footpath2.map.IndoorLocation;
import de.uvwxy.footpath2.tools.GeoUtils;

public class OSM2DBuilding implements DrawToCanvas{
	public  LinkedList<GraphEdge> walls_outer = new LinkedList<GraphEdge>();
	public  LinkedList<GraphEdge> walls_inner = new LinkedList<GraphEdge>();
	public  LinkedList<LinkedList<GraphEdge>> walls_outer_area = new LinkedList<LinkedList<GraphEdge>>();
	public  LinkedList<LinkedList<GraphEdge>> walls_inner_area = new LinkedList<LinkedList<GraphEdge>>();
	
	public  LinkedList<GraphEdge> stairs = new LinkedList<GraphEdge>();
	public  LinkedList<GraphEdge> elevators = new LinkedList<GraphEdge>();
	public  LinkedList<LinkedList<GraphEdge>> stairs_area = new LinkedList<LinkedList<GraphEdge>>();
	public  LinkedList<LinkedList<GraphEdge>> elevators_area = new LinkedList<LinkedList<GraphEdge>>();
	
	private float current_level = 2.0f;
	
	public void setCurrent_level(float current_level) {
		this.current_level = current_level;
	}
	public float getCurrent_level() {
		return current_level;
	}
	
	@Override
	public synchronized void drawToCanvas(Canvas canvas, IndoorLocation center, Rect boundingBox,
			double pixelsPerMeterOrMaxValue, Paint pLine, Paint pDots) {
		int w = boundingBox.width() / 2 + boundingBox.left;
		int h = boundingBox.height() / 2 + boundingBox.top;

		if (canvas == null || center == null || pLine == null || pDots == null) {
			return;
		}

		pLine.setColor(Color.WHITE);
		for (int i = 0; i < walls_inner.size() - 1; i++) {
			int[] apix = GeoUtils.convertToPixelLocation(walls_inner.get(i).getNode0(), center, pixelsPerMeterOrMaxValue);
			int[] bpix = GeoUtils.convertToPixelLocation(walls_inner.get(i).getNode1(), center, pixelsPerMeterOrMaxValue);
			canvas.drawLine(w + apix[0], h + apix[1], w + bpix[0], h + bpix[1], pLine);
		}
		pLine.setColor(Color.RED);
		for (int i = 0; i < stairs.size() - 1; i++) {
			int[] apix = GeoUtils.convertToPixelLocation(stairs.get(i).getNode0(), center, pixelsPerMeterOrMaxValue);
			int[] bpix = GeoUtils.convertToPixelLocation(stairs.get(i).getNode1(), center, pixelsPerMeterOrMaxValue);
			canvas.drawLine(w + apix[0], h + apix[1], w + bpix[0], h + bpix[1], pLine);
		}

		pLine.setColor(Color.GREEN);
		for (int i = 0; i < elevators.size() - 1; i++) {
			int[] apix = GeoUtils.convertToPixelLocation(elevators.get(i).getNode0(), center, pixelsPerMeterOrMaxValue);
			int[] bpix = GeoUtils.convertToPixelLocation(elevators.get(i).getNode1(), center, pixelsPerMeterOrMaxValue);
			canvas.drawLine(w + apix[0], h + apix[1], w + bpix[0], h + bpix[1], pLine);
		}

	}
}
