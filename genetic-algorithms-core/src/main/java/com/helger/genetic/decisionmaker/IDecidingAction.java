/**
 * Copyright (C) 2012-2019 Philip Helger
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.genetic.decisionmaker;

import java.io.Serializable;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Base interface for an action that uses an external decision maker.
 * 
 * @author Philip Helger
 */
public interface IDecidingAction extends Serializable
{
  /**
   * @return The underlying decision maker. Never <code>null</code>.
   */
  @Nonnull
  IDecisionMaker getDecisionMaker ();

  /**
   * @return Number of tried actions.
   */
  @Nonnegative
  int getTryCount ();

  /**
   * @return Number of real actions executed, dependent on the used decision
   *         maker. Always &le; than the try count.
   */
  @Nonnegative
  int getExecutionCount ();
}
