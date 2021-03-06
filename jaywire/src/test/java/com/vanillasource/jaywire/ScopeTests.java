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

package com.vanillasource.jaywire;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.function.Supplier;

@Test
public class ScopeTests {
   public void testAppliedSupplierDelegatesToGet() {
      ScopeImpl scope = new ScopeImpl();
      Supplier<Object> appliedSupplier = scope.apply(() -> new Object());

      Object result = appliedSupplier.get();

      assertSame(result, scope.instance);
   }

   public static class ScopeImpl implements Scope {
      private Object instance;

      @Override
      @SuppressWarnings("unchecked")
      public <T> T get(Factory<T> supplier) {
         return (T) instance;
      }
   }
}

