/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.opengl;

import org.lwjgl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.system.Checks.*;
import static org.lwjgl.system.JNI.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Native bindings to the <a href="http://www.opengl.org/registry/specs/EXT/texture_from_pixmap.txt">GLX_EXT_texture_from_pixmap</a> extension.
 * 
 * <p>This extension allows a color buffer to be used for both rendering and texturing.</p>
 * 
 * <p>Requires {@link GLX13 GLX 1.3}.</p>
 */
public final class GLXEXTTextureFromPixmap {

	/** Accepted by the {@code attribute} parameter of {@link GLX13#glXGetFBConfigAttrib GetFBConfigAttrib} and the {@code attrib_list} parameter of {@link GLX13#glXChooseFBConfig ChooseFBConfig}. */
	public static final int
		GLX_BIND_TO_TEXTURE_RGB_EXT     = 0x20D0,
		GLX_BIND_TO_TEXTURE_RGBA_EXT    = 0x20D1,
		GLX_BIND_TO_MIPMAP_TEXTURE_EXT  = 0x20D2,
		GLX_BIND_TO_TEXTURE_TARGETS_EXT = 0x20D3,
		GLX_Y_INVERTED_EXT              = 0x20D4;

	/** Accepted as an attribute in the {@code attrib_list} parameter of {@link GLX13#glXCreatePixmap CreatePixmap}, and by the {@code attribute} parameter of {@link GLX13#glXQueryDrawable QueryDrawable}. */
	public static final int
		GLX_TEXTURE_FORMAT_EXT = 0x20D5,
		GLX_TEXTURE_TARGET_EXT = 0x20D6,
		GLX_MIPMAP_TEXTURE_EXT = 0x20D7;

	/**
	 * Accepted as a value in the {@code attrib_list} parameter of {@link GLX13#glXCreatePixmap CreatePixmap} and returned in the {@code value} parameter of
	 * {@link GLX13#glXQueryDrawable QueryDrawable} when {@code attribute} is {@link #GLX_TEXTURE_FORMAT_EXT TEXTURE_FORMAT_EXT}.
	 */
	public static final int
		GLX_TEXTURE_FORMAT_NONE_EXT = 0x20D8,
		GLX_TEXTURE_FORMAT_RGB_EXT  = 0x20D9,
		GLX_TEXTURE_FORMAT_RGBA_EXT = 0x20DA;

	/** Accepted as bits in the {@link #GLX_BIND_TO_TEXTURE_TARGETS_EXT BIND_TO_TEXTURE_TARGETS_EXT} variable. */
	public static final int
		GLX_TEXTURE_1D_BIT_EXT        = 0x1,
		GLX_TEXTURE_2D_BIT_EXT        = 0x2,
		GLX_TEXTURE_RECTANGLE_BIT_EXT = 0x4;

	/**
	 * Accepted as a value in the {@code attrib_list} parameter of {@link GLX13#glXCreatePixmap CreatePixmap} and returned in the {@code value} parameter of
	 * {@link GLX13#glXQueryDrawable QueryDrawable} when {@code attribute} is {@link #GLX_TEXTURE_TARGET_EXT TEXTURE_TARGET_EXT}.
	 */
	public static final int
		GLX_TEXTURE_1D_EXT        = 0x20DB,
		GLX_TEXTURE_2D_EXT        = 0x20DC,
		GLX_TEXTURE_RECTANGLE_EXT = 0x20DD;

	/** Accepted by the {@code buffer} parameter of {@link #glXBindTexImageEXT BindTexImageEXT} and {@link #glXReleaseTexImageEXT ReleaseTexImageEXT}. */
	public static final int
		GLX_FRONT_LEFT_EXT  = 0x20DE,
		GLX_FRONT_RIGHT_EXT = 0x20DF,
		GLX_BACK_LEFT_EXT   = 0x20E0,
		GLX_BACK_RIGHT_EXT  = 0x20E1,
		GLX_FRONT_EXT       = GLX_FRONT_LEFT_EXT,
		GLX_BACK_EXT        = GLX_BACK_LEFT_EXT,
		GLX_AUX0_EXT        = 0x20E2,
		GLX_AUX1_EXT        = 0x20E3,
		GLX_AUX2_EXT        = 0x20E4,
		GLX_AUX3_EXT        = 0x20E5,
		GLX_AUX4_EXT        = 0x20E6,
		GLX_AUX5_EXT        = 0x20E7,
		GLX_AUX6_EXT        = 0x20E8,
		GLX_AUX7_EXT        = 0x20E9,
		GLX_AUX8_EXT        = 0x20EA,
		GLX_AUX9_EXT        = 0x20EB;

	/** Function address. */
	@JavadocExclude
	public final long
		BindTexImageEXT,
		ReleaseTexImageEXT;

	@JavadocExclude
	public GLXEXTTextureFromPixmap(FunctionProvider provider) {
		BindTexImageEXT = provider.getFunctionAddress("glXBindTexImageEXT");
		ReleaseTexImageEXT = provider.getFunctionAddress("glXReleaseTexImageEXT");
	}

	// --- [ Function Addresses ] ---

	/** Returns the {@link GLXEXTTextureFromPixmap} instance for the current context. */
	public static GLXEXTTextureFromPixmap getInstance() {
		return checkFunctionality(GL.getCapabilities().__GLXEXTTextureFromPixmap);
	}

	static GLXEXTTextureFromPixmap create(java.util.Set<String> ext, FunctionProvider provider) {
		if ( !ext.contains("GLX_EXT_texture_from_pixmap") ) return null;

		GLXEXTTextureFromPixmap funcs = new GLXEXTTextureFromPixmap(provider);

		boolean supported = checkFunctions(
			funcs.BindTexImageEXT, funcs.ReleaseTexImageEXT
		);

		return GL.checkExtension("GLX_EXT_texture_from_pixmap", funcs, supported);
	}

	// --- [ glXBindTexImageEXT ] ---

	/** Unsafe version of {@link #glXBindTexImageEXT BindTexImageEXT} */
	@JavadocExclude
	public static void nglXBindTexImageEXT(long display, long drawable, int buffer, long attrib_list) {
		long __functionAddress = getInstance().BindTexImageEXT;
		if ( LWJGLUtil.CHECKS ) {
			checkPointer(display);
			checkPointer(drawable);
		}
		callPPIPV(__functionAddress, display, drawable, buffer, attrib_list);
	}

	/**
	 * Defines a one- or two-dimensional texture image. The texture image is taken from {@code buffer} and need not be copied. The texture target, the texture
	 * format, and the size of the texture components are derived from attributes of {@code drawable}.
	 *
	 * @param display     the connection to the X server
	 * @param drawable    the drawable
	 * @param buffer      the buffer
	 * @param attrib_list an optional null-terminated list of attributes
	 */
	public static void glXBindTexImageEXT(long display, long drawable, int buffer, ByteBuffer attrib_list) {
		if ( LWJGLUtil.CHECKS )
			if ( attrib_list != null ) checkNT4(attrib_list);
		nglXBindTexImageEXT(display, drawable, buffer, memAddressSafe(attrib_list));
	}

	/** Alternative version of: {@link #glXBindTexImageEXT BindTexImageEXT} */
	public static void glXBindTexImageEXT(long display, long drawable, int buffer, IntBuffer attrib_list) {
		if ( LWJGLUtil.CHECKS )
			if ( attrib_list != null ) checkNT(attrib_list);
		nglXBindTexImageEXT(display, drawable, buffer, memAddressSafe(attrib_list));
	}

	// --- [ glXReleaseTexImageEXT ] ---

	/**
	 * Releases a color buffer that is being used as a texture.
	 *
	 * @param display  the connection to the X server
	 * @param drawable the drawable
	 * @param buffer   the buffer
	 */
	public static void glXReleaseTexImageEXT(long display, long drawable, int buffer) {
		long __functionAddress = getInstance().ReleaseTexImageEXT;
		if ( LWJGLUtil.CHECKS ) {
			checkPointer(display);
			checkPointer(drawable);
		}
		callPPIV(__functionAddress, display, drawable, buffer);
	}

}