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

package com.vanillasource.jaywire.web;

import com.vanillasource.jaywire.RequestScopeSupport;
import com.vanillasource.jaywire.SingletonScopeSupport;
import com.vanillasource.jaywire.SessionScopeSupport;
import com.vanillasource.jaywire.Scope;
import com.vanillasource.jaywire.serialization.SerializationSupport;

/**
 * A module implementation that provides a session scope based on
 * the request scope. The request scope has to be opened before
 * the session is opened.
 */
public interface WeakSessionScopeModule
   extends SingletonScopeSupport, RequestScopeSupport, SessionScopeSupport, SerializationSupport {

   default WeakSessionScope getWeakSessionScope() {
      return singleton(() -> new WeakSessionScope(getRequestScope()));
   }

   @Override
   default Scope getSessionScope() {
      return singleton(() -> makeSupplierSerializable(getWeakSessionScope()));
   }
}

