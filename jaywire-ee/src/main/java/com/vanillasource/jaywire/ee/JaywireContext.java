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

package com.vanillasource.jaywire.ee;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.lang.annotation.Annotation;
import java.io.Serializable;

public class JaywireContext implements Context, Serializable {
   @Override
   public Class<? extends Annotation> getScope() {
      return Jaywire.class;
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public <T> T get(Contextual<T> contextual) {
      return get(contextual, null);
   }

   @Override
   public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
      return contextual.create(creationalContext);
   }
}
