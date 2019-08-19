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
package com.helger.genetic.selector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.genetic.eventhandler.IEventHandler;
import com.helger.genetic.model.chromosome.IChromosome;

/**
 * Meta selector that chooses every n generation between 2 different other
 * selectors.
 * 
 * @author Philip Helger
 */
public class SelectorAlternating extends AbstractSelector
{
  private final ISelector m_aCS1;
  private final ISelector m_aCS2;
  private ISelector m_aCS;
  private final int m_nAlternatingGenerationCount;
  private final IEventHandler m_aEventHandler;

  public SelectorAlternating (@Nonnull final ISelector aCS1,
                              @Nonnull final ISelector aCS2,
                              @Nonnegative final int nAlternatingGenerationCount,
                              @Nonnull final IEventHandler aEventHandler)
  {
    super ();
    ValueEnforcer.notNull (aCS1, "CrossoverSelector1");
    ValueEnforcer.notNull (aCS2, "CrossoverSelector2");
    ValueEnforcer.isGT0 (nAlternatingGenerationCount, "AlternatingGenerationCount");
    ValueEnforcer.notNull (aEventHandler, "EventHandler");
    m_aCS1 = aCS1;
    m_aCS2 = aCS2;
    m_aCS = aCS1;
    m_nAlternatingGenerationCount = nAlternatingGenerationCount;
    m_aEventHandler = aEventHandler;
  }

  /**
   * Invoked each time the cross over selector is changed
   *
   * @param aNewCS
   *        The new crossover selector to be used. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onCrossoverSelectionAlternation (@Nonnull final ISelector aNewCS)
  {}

  @Nonnull
  public ICommonsList <IChromosome> selectSurvivingChromosomes (@Nonnull final ICommonsList <IChromosome> aChromosomes)
  {
    if ((m_aEventHandler.getLastGeneration () % m_nAlternatingGenerationCount) == 0)
    {
      // At this generation switch to other crossover selector
      m_aCS = m_aCS == m_aCS2 ? m_aCS1 : m_aCS2;
      onCrossoverSelectionAlternation (m_aCS);
    }
    return m_aCS.selectSurvivingChromosomes (aChromosomes);
  }
}
