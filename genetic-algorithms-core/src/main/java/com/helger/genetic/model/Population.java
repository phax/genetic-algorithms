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
package com.helger.genetic.model;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsIterable;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.genetic.model.chromosome.IChromosome;

/**
 * Default implementation of {@link IPopulation} and {@link IMutablePopulation}.
 * 
 * @author Philip Helger
 */
public class Population implements IMutablePopulation
{
  private final long m_nGeneration;
  private final ICommonsList <IChromosome> m_aChromosomes = new CommonsArrayList <> ();
  // Status cache only
  private IChromosome m_aFittestChromosome;

  public Population (@Nonnegative final long nGeneration)
  {
    ValueEnforcer.isGE0 (nGeneration, "Generation");
    m_nGeneration = nGeneration;
  }

  @Nonnegative
  public final long getGeneration ()
  {
    return m_nGeneration;
  }

  @Nonnegative
  public int getChromosomeCount ()
  {
    return m_aChromosomes.size ();
  }

  @Nonnull
  public IChromosome getChromosome (@Nonnegative final int nIndex)
  {
    return m_aChromosomes.get (nIndex);
  }

  @Nonnull
  @ReturnsMutableObject
  public ICommonsIterable <IChromosome> getChromosomes ()
  {
    // ESCA-JAVA0259:
    return m_aChromosomes;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IChromosome> getAllChromosomes ()
  {
    return m_aChromosomes.getClone ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public IChromosome [] getChromosomeArray ()
  {
    return m_aChromosomes.toArray (new IChromosome [m_aChromosomes.size ()]);
  }

  public void addChromosome (@Nonnull final IChromosome aChromosome)
  {
    ValueEnforcer.notNull (aChromosome, "Chromosome");

    m_aChromosomes.add (aChromosome);
    // Reset cache
    m_aFittestChromosome = null;
  }

  public void addChromosomes (@Nonnull final Iterable <? extends IChromosome> aChromosomes)
  {
    ValueEnforcer.notNull (aChromosomes, "Chromosomes");

    m_aChromosomes.addAll (aChromosomes);
    // Reset cache
    m_aFittestChromosome = null;
  }

  public void setChromosome (@Nonnegative final int nIndex, @Nonnull final IChromosome aChromosome)
  {
    ValueEnforcer.notNull (aChromosome, "Chromosome");

    m_aChromosomes.set (nIndex, aChromosome);
    // Reset cache
    m_aFittestChromosome = null;
  }

  public void removeAllChromosomes ()
  {
    m_aChromosomes.clear ();
    m_aFittestChromosome = null;
  }

  @Nonnull
  public IChromosome getFittestChromosome ()
  {
    if (m_aFittestChromosome == null)
    {
      IChromosome aFittestChromosome = null;
      for (final IChromosome aCurrentChromosome : m_aChromosomes)
        if (aFittestChromosome == null || aCurrentChromosome.isFitterThan (aFittestChromosome))
          aFittestChromosome = aCurrentChromosome;
      m_aFittestChromosome = aFittestChromosome;
    }
    return m_aFittestChromosome;
  }
}
