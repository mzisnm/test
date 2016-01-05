package com.github.ustc_zzzz.fmltutor.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

/**
 * 
 * @author 马哲@zhuzher
 * @since 2016年1月5日下午6:09:02
 * @version
 *
 */
public class GuiGlTest extends GuiScreen {
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.enableBlend();
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		worldRenderer.startDrawing(GL11.GL_TRIANGLES);;
		worldRenderer.setTranslation(-1.5f, 0.0f, -6.0f);
		worldRenderer.setColorOpaque_F(1.0f,0.0f,0.0f);
		worldRenderer.addVertex(0.0f, 1.0f, 0.0f);
		worldRenderer.setColorOpaque_F(0.0f,1.0f,0.0f);
		worldRenderer.addVertex(-1.0f, -1.0f, 0.0f);
		worldRenderer.setColorOpaque_F(0.0f,0.0f,1.0f);
		worldRenderer.addVertex(1.0f, -1.0f, 0.0f);
		tessellator.draw();
		//GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		//GL11.glLoadIdentity();
		//GL11.glTranslatef(-1.5f, 0.0f, -6.0f);
		//GL11.glBegin(GL11.GL_TRIANGLES); // 绘制三角形
		//GL11.glColor3f(1.0f,0.0f,0.0f);	
		//GL11.glVertex3f(0.0f, 1.0f, 0.0f); // 上顶点
		/*GL11.glColor3f(0.0f,1.0f,0.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 0.0f); // 左下
		GL11.glColor3f(0.0f,0.0f,1.0f);
		GL11.glVertex3f(1.0f, -1.0f, 0.0f); // 右下
		GL11.glEnd();
		GL11.glTranslatef(3.0f, 0.0f, 0.0f);
		GL11.glColor3f(0.5f,0.5f,1.0f);
		GL11.glBegin(GL11.GL_QUADS); // 绘制正方形
		GL11.glVertex3f(-1.0f, 1.0f, 0.0f); // 左上
		GL11.glVertex3f(1.0f, 1.0f, 0.0f); // 右上
		GL11.glVertex3f(1.0f, -1.0f, 0.0f); // 左下
		GL11.glVertex3f(-1.0f, -1.0f, 0.0f); // 右下
		GL11.glEnd();*/
	        GlStateManager.disableBlend();
	}
}
