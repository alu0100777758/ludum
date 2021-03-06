/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.system.jemalloc;

import java.nio.*;

import org.lwjgl.*;
import org.lwjgl.system.libffi.*;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.libffi.LibFFI.*;

/** Instances of this interface may be passed to the {@link JEmalloc#je_malloc_usable_size} method. */
public abstract class MallocMessageCallback extends Closure.Void {

	private static final ByteBuffer    CIF  = staticAlloc(FFICIF.SIZEOF);
	private static final PointerBuffer ARGS = staticAllocPointer(2);

	static {
		prepareCIF(
			"MallocMessageCallback",
			CALL_CONVENTION_DEFAULT,
			CIF, ffi_type_void,
			ARGS, ffi_type_pointer, ffi_type_pointer
		);
	}

	protected MallocMessageCallback() {
		super(CIF);
	}

	/**
	 * Will be called from a libffi closure invocation. Decodes the arguments and passes them to {@link #invoke}.
	 *
	 * @param args pointer to an array of jvalues
	 */
	@Override
	protected void callback(long args) {
		invoke(
			memGetAddress(memGetAddress(POINTER_SIZE * 0 + args)),
			memGetAddress(memGetAddress(POINTER_SIZE * 1 + args))
		);
	}

	/**
	 * Will be called by the JEmalloc##je_malloc_usable_size() method.
	 *
	 * @param cbopaque the opaque pointer passed to {@link JEmalloc#je_malloc_usable_size}
	 * @param s        the message
	 */
	public abstract void invoke(long cbopaque, long s);

	/** A functional interface for {@link MallocMessageCallback}. */
	public interface SAM {
		void invoke(long cbopaque, long s);
	}

}