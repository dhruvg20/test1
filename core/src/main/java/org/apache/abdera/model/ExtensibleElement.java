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
package org.apache.abdera.model;

import java.util.List;

import javax.xml.namespace.QName;

public interface ExtensibleElement extends Element {

  /**
   * Returns the complete set of extension elements
   */
  List<Element> getExtensions();
  
  /**
   * Returns the complete set of extension elements using the specified 
   * XML Namespace URI
   */
  List<Element> getExtensions(String uri);
  
  /**
   * Returns the complete set of extension elements using the specified
   * XML qualified name
   */
  <T extends Element>List<T> getExtensions(QName qname);
  
  /**
   * Returns the first extension element with the XML qualified name
   */
  <T extends Element>T getExtension(QName qname);
  
  /**
   * Adds an individual extension element
   */
  void addExtension(Element extension);

  /**
   * Adds an individual extension element
   */
  <T extends Element>T addExtension(QName qname);
  
  /**
   * Adds an individual extension element
   */
  <T extends Element>T addExtension(
    String namespace, 
    String localPart,
    String prefix);
  
  /**
   * Adds a simple extension (text content only)
   */
  Element addSimpleExtension(
    QName qname, 
    String value);
  
  /**
   * Adds a simple extension (text content only)
   */
  Element addSimpleExtension(
    String namespace, 
    String localPart, 
    String prefix, 
    String value);
  
  /**
   * Gets the value of a simple extension
   */
  String getSimpleExtension(QName qname);
  
  /**
   * Gets the value of a simple extension
   */
  String getSimpleExtension(String namespace, String localPart, String prefix);
  
  /**
   * Find an extension by Class rather than QName
   */
  <T extends Element> T getExtension(Class<T> _class);
}
