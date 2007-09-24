/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  The ASF licenses this file to You
* under the Apache License, Version 2.0 (the "License"); you may not
* use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.  For additional information regarding
* copyright in this work, please see the NOTICE file in the top level
* directory of this distribution.
*/
package org.apache.abdera.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;

import org.apache.abdera.Abdera;
import org.apache.abdera.converter.annotation.Feed;
import org.apache.abdera.converter.annotation.Entry;

public class ConventionConversionContext 
  extends DefaultConversionContext {

  private static final long serialVersionUID = 7504071837124132972L;
  
  protected Conventions conventions;
  
  public ConventionConversionContext() {
    this(new Abdera());
  }

  public ConventionConversionContext(
    Conventions conventions) {
      this(null,conventions);
  }
  
  public ConventionConversionContext(
    Abdera abdera) {
      super(abdera);
      this.conventions = new DefaultConventions();
      initConverters();
  }
  
  public ConventionConversionContext(
    Abdera abdera, 
    Conventions conventions) {
      super(abdera);
      this.conventions = conventions;
      initConverters();
  }

  public Conventions getConventions() {
    return conventions;
  }
  
  public void setConventions(Conventions conventions) {
    this.conventions = conventions;
  }
  
  public boolean hasConverter(AccessibleObject accessor) {
    boolean answer = super.hasConverter(accessor);
    if (answer) return true;
    Class<? extends Annotation> annotation = 
      getConventions().matchConvention(accessor);
    return annotation != null && hasConverter(annotation);
  }
  
  private void initConverters() {
    for (Class<? extends Annotation> type : annotations) {
      org.apache.abdera.converter.annotation.Converter converter = 
        type.getAnnotation(org.apache.abdera.converter.annotation.Converter.class);
      if (converter != null) {
        try {
          Converter<? extends Object> conv = converter.value().newInstance(); 
          this.setConverter(type,conv);
        } catch (Throwable t) {
          throw new ConversionException(t);
        }
      }
    }
  }
  
  @SuppressWarnings("unchecked") private static final Class[] annotations = 
  {Feed.class, Entry.class};
}