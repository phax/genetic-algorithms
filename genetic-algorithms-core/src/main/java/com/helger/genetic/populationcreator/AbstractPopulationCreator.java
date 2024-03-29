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
package com.helger.genetic.populationcreator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.genetic.model.IMutablePopulation;
import com.helger.genetic.model.Population;

public abstract class AbstractPopulationCreator implements IPopulationCreator
{
  private long m_nGeneration = 0;

  @Nonnegative
  public final long getCurrentGeneration ()
  {
    return m_nGeneration;
  }

  @Nonnull
  public final IMutablePopulation createEmptyPopulation ()
  {
    final Population ret = new Population (m_nGeneration);
    m_nGeneration++;
    return ret;
  }
}
