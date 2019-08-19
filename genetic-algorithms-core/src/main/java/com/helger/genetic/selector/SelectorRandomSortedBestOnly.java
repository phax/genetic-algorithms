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
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.genetic.model.chromosome.IChromosome;
import com.helger.genetic.utils.random.RandomGenerator;

/**
 * Cross over selector:
 * <ul>
 * <li>Take a random subset of chromosomes to choose from</li>
 * <li>Sorted the chosen chromosomes by their fitness</li>
 * <li>Always use the absolute fittest chromosome</li>
 * </ul>
 *
 * @author Philip Helger
 */
public class SelectorRandomSortedBestOnly extends AbstractSelector
{
  private final int m_nTournamentSize;

  public SelectorRandomSortedBestOnly (@Nonnegative final int nTournamentSize)
  {
    ValueEnforcer.isGT0 (nTournamentSize, "TournamentSize");
    m_nTournamentSize = nTournamentSize;
  }

  @Nonnegative
  public final int getTournamentSize ()
  {
    return m_nTournamentSize;
  }

  @Nonnull
  public ICommonsList <IChromosome> selectSurvivingChromosomes (@Nonnull final ICommonsList <IChromosome> aChromosomes)
  {
    final int nChromosomes = aChromosomes.size ();

    // Randomly choose tournament participants
    final int [] aSelected = RandomGenerator.getMultipleUniqueIntsInRange (m_nTournamentSize, nChromosomes);

    // Retrieve the tournament participants
    final ICommonsList <IChromosome> aChosen = new CommonsArrayList <> (m_nTournamentSize);
    for (final int nSelected : aSelected)
      aChosen.add (aChromosomes.get (nSelected));

    // Sort all chosen chromosomes by descending fitness
    final IChromosome aFittestChromosome = aChosen.getSortedInline (IChromosome.comparatorFitness ().reversed ())
                                                  .getFirst ();
    final ICommonsList <IChromosome> ret = new CommonsArrayList <> (nChromosomes);
    for (int i = 0; i < nChromosomes; ++i)
      ret.add (aFittestChromosome);
    return ret;
  }
}
