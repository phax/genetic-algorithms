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
package com.helger.genetic.tsp.mutation;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.genetic.decisionmaker.DecisionMakerAlways;
import com.helger.genetic.eventhandler.IEventHandler;
import com.helger.genetic.model.chromosome.IChromosome;
import com.helger.genetic.mutation.AbstractMutation;
import com.helger.matrix.Matrix;

/**
 * A mutation that always performs a greedy optimization for the first 20
 * generations, and than uses the supplied mutation.
 *
 * @author Philip Helger
 */
public class TSPMutationGreedyBeginning extends AbstractMutation
{
  private final IEventHandler m_aEventHandler;
  private final TSPMutationGreedy m_aFirst;
  private final AbstractMutation m_aLast;
  private boolean m_bGreedyMode = true;

  public TSPMutationGreedyBeginning (@Nonnull final Matrix aDistanceMatrix,
                                     @Nonnull final IEventHandler aEventHandler,
                                     @Nonnull final AbstractMutation aMutation)
  {
    super (DecisionMakerAlways.getInstance ());
    ValueEnforcer.notNull (aDistanceMatrix, "DistanceMatrix");
    ValueEnforcer.notNull (aEventHandler, "EventHandler");
    ValueEnforcer.notNull (aMutation, "Mutation");

    m_aEventHandler = aEventHandler;
    m_aFirst = new TSPMutationGreedy (DecisionMakerAlways.getInstance (), aDistanceMatrix);
    m_aLast = aMutation;
  }

  @Override
  @Nonnull
  public IChromosome executeMutation (@Nonnull final IChromosome aChromosome)
  {
    return m_bGreedyMode ? m_aFirst.executeMutation (aChromosome) : m_aLast.executeMutation (aChromosome);
  }

  @Override
  @Nonnull
  public ICommonsList <IChromosome> mutate (@Nonnull final ICommonsList <IChromosome> aChromosomes)
  {
    if (m_bGreedyMode && m_aEventHandler.getLastGeneration () >= 15)
    {
      m_bGreedyMode = false;
      // Set the new decision maker
      setDecisionMaker (m_aLast.getDecisionMaker ());
    }

    return super.mutate (aChromosomes);
  }
}
