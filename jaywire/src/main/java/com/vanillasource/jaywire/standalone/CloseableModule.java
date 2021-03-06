/**
  * Copyright (C) 2015 VanillaSource
  *
  * This library is free software; you can redistribute it and/or
  * modify it under the terms of the GNU Lesser General Public
  * License as published by the Free Software Foundation; either
  * version 3 of the License, or (at your option) any later version.
  *
  * This library is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  * Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public
  * License along with this library; if not, write to the Free Software
  * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
  */

package com.vanillasource.jaywire.standalone;

import com.vanillasource.jaywire.Scope;
import com.vanillasource.jaywire.CloseableSupport;
import com.vanillasource.jaywire.SingletonScopeSupport;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Implements being closeable, and closing registered closeables automatically
 * as a mixin to any singleton supporting module.
 */
public interface CloseableModule extends CloseableSupport, SingletonScopeSupport {
   default Collection<AutoCloseable> getCloseables() {
      return singleton(() -> new ConcurrentLinkedDeque<AutoCloseable>());
   }

   @Override
   default void closeWithModule(AutoCloseable closeable) {
      getCloseables().add(closeable);
   }

   /**
    * Tries to close all registered objects, but will abort at the first
    * failure with the exception thrown from closeable.
    */
   @Override
   default void close() throws Exception {
      for (AutoCloseable closeable : getCloseables()) {
         closeable.close();
      }
   }
}

