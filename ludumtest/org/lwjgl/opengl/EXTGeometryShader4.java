/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.opengl;

import org.lwjgl.*;
import org.lwjgl.system.*;

import static org.lwjgl.system.Checks.*;
import static org.lwjgl.system.JNI.*;

/**
 * Native bindings to the <a href="http://www.opengl.org/registry/specs/EXT/geometry_shader4.txt">EXT_geometry_shader4</a> extension.
 * 
 * <p>EXT_geometry_shader4 defines a new shader type available to be run on the GPU, called a geometry shader. Geometry shaders are run after vertices are
 * transformed, but prior to color clamping, flat shading and clipping.</p>
 * 
 * <p>A geometry shader begins with a single primitive (point, line, triangle). It can read the attributes of any of the vertices in the primitive and use
 * them to generate new primitives. A geometry shader has a fixed output primitive type (point, line strip, or triangle strip) and emits vertices to
 * define a new primitive. A geometry shader can emit multiple disconnected primitives. The primitives emitted by the geometry shader are clipped and then
 * processed like an equivalent OpenGL primitive specified by the application.</p>
 * 
 * <p>Furthermore, EXT_geometry_shader4 provides four additional primitive types: lines with adjacency, line strips with adjacency, separate triangles with
 * adjacency, and triangle strips with adjacency. Some of the vertices specified in these new primitive types are not part of the ordinary primitives,
 * instead they represent neighboring vertices that are adjacent to the two line segment end points (lines/strips) or the three triangle edges
 * (triangles/tstrips). These vertices can be accessed by geometry shaders and used to match up the vertices emitted by the geometry shader with those of
 * neighboring primitives.</p>
 * 
 * <p>Since geometry shaders expect a specific input primitive type, an error will occur if the application presents primitives of a different type. For
 * example, if a geometry shader expects points, an error will occur at Begin() time, if a primitive mode of TRIANGLES is specified.</p>
 */
public final class EXTGeometryShader4 {

	/** Accepted by the {@code type} parameter of CreateShader and returned by the {@code params} parameter of GetShaderiv. */
	public static final int GL_GEOMETRY_SHADER_EXT = 0x8DD9;

	/** Accepted by the {@code pname} parameter of ProgramParameteriEXT and GetProgramiv. */
	public static final int
		GL_GEOMETRY_VERTICES_OUT_EXT = 0x8DDA,
		GL_GEOMETRY_INPUT_TYPE_EXT   = 0x8DDB,
		GL_GEOMETRY_OUTPUT_TYPE_EXT  = 0x8DDC;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS_EXT     = 0x8C29,
		GL_MAX_GEOMETRY_VARYING_COMPONENTS_EXT      = 0x8DDD,
		GL_MAX_VERTEX_VARYING_COMPONENTS_EXT        = 0x8DDE,
		GL_MAX_VARYING_COMPONENTS_EXT               = 0x8B4B,
		GL_MAX_GEOMETRY_UNIFORM_COMPONENTS_EXT      = 0x8DDF,
		GL_MAX_GEOMETRY_OUTPUT_VERTICES_EXT         = 0x8DE0,
		GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS_EXT = 0x8DE1;

	/** Accepted by the {@code mode} parameter of Begin, DrawArrays, MultiDrawArrays, DrawElements, MultiDrawElements, and DrawRangeElements. */
	public static final int
		GL_LINES_ADJACENCY_EXT          = 0xA,
		GL_LINE_STRIP_ADJACENCY_EXT     = 0xB,
		GL_TRIANGLES_ADJACENCY_EXT      = 0xC,
		GL_TRIANGLE_STRIP_ADJACENCY_EXT = 0xD;

	/** Returned by CheckFramebufferStatusEXT. */
	public static final int
		GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS_EXT = 0x8DA8,
		GL_FRAMEBUFFER_INCOMPLETE_LAYER_COUNT_EXT   = 0x8DA9;

	/** Accepted by the {@code pname} parameter of GetFramebufferAttachment- ParameterivEXT. */
	public static final int
		GL_FRAMEBUFFER_ATTACHMENT_LAYERED_EXT       = 0x8DA7,
		GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER_EXT = 0x8CD4;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, and by the {@code pname} parameter of GetIntegerv, GetFloatv, GetDoublev, and
	 * GetBooleanv.
	 */
	public static final int GL_PROGRAM_POINT_SIZE_EXT = 0x8642;

	/** Function address. */
	@JavadocExclude
	public final long
		ProgramParameteriEXT,
		FramebufferTextureEXT,
		FramebufferTextureLayerEXT,
		FramebufferTextureFaceEXT;

	@JavadocExclude
	public EXTGeometryShader4(FunctionProvider provider) {
		ProgramParameteriEXT = provider.getFunctionAddress("glProgramParameteriEXT");
		FramebufferTextureEXT = provider.getFunctionAddress("glFramebufferTextureEXT");
		FramebufferTextureLayerEXT = provider.getFunctionAddress("glFramebufferTextureLayerEXT");
		FramebufferTextureFaceEXT = provider.getFunctionAddress("glFramebufferTextureFaceEXT");
	}

	// --- [ Function Addresses ] ---

	/** Returns the {@link EXTGeometryShader4} instance for the current context. */
	public static EXTGeometryShader4 getInstance() {
		return checkFunctionality(GL.getCapabilities().__EXTGeometryShader4);
	}

	static EXTGeometryShader4 create(java.util.Set<String> ext, FunctionProvider provider) {
		if ( !ext.contains("GL_EXT_geometry_shader4") ) return null;

		EXTGeometryShader4 funcs = new EXTGeometryShader4(provider);

		boolean supported = checkFunctions(
			funcs.ProgramParameteriEXT, funcs.FramebufferTextureEXT, funcs.FramebufferTextureLayerEXT, funcs.FramebufferTextureFaceEXT
		);

		return GL.checkExtension("GL_EXT_geometry_shader4", funcs, supported);
	}

	// --- [ glProgramParameteriEXT ] ---

	public static void glProgramParameteriEXT(int program, int pname, int value) {
		long __functionAddress = getInstance().ProgramParameteriEXT;
		callIIIV(__functionAddress, program, pname, value);
	}

	// --- [ glFramebufferTextureEXT ] ---

	public static void glFramebufferTextureEXT(int target, int attachment, int texture, int level) {
		long __functionAddress = getInstance().FramebufferTextureEXT;
		callIIIIV(__functionAddress, target, attachment, texture, level);
	}

	// --- [ glFramebufferTextureLayerEXT ] ---

	public static void glFramebufferTextureLayerEXT(int target, int attachment, int texture, int level, int layer) {
		long __functionAddress = getInstance().FramebufferTextureLayerEXT;
		callIIIIIV(__functionAddress, target, attachment, texture, level, layer);
	}

	// --- [ glFramebufferTextureFaceEXT ] ---

	public static void glFramebufferTextureFaceEXT(int target, int attachment, int texture, int level, int face) {
		long __functionAddress = getInstance().FramebufferTextureFaceEXT;
		callIIIIIV(__functionAddress, target, attachment, texture, level, face);
	}

}