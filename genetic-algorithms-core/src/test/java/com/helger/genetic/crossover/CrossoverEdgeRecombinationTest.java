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
package com.helger.genetic.crossover;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.genetic.decisionmaker.DecisionMakerAlways;
import com.helger.genetic.model.MockFitnessFunction;
import com.helger.genetic.model.chromosome.Chromosome;
import com.helger.genetic.model.chromosome.IChromosome;

/**
 * Test class for class {@link CrossoverEdgeRecombination}.
 *
 * @author Philip Helger
 */
public final class CrossoverEdgeRecombinationTest
{
  private static final Logger LOGGER = LoggerFactory.getLogger (CrossoverEdgeRecombinationTest.class);

  @Test
  public void testBasic ()
  {
    final CrossoverEdgeRecombination aCrossover = new CrossoverEdgeRecombination (DecisionMakerAlways.getInstance ());
    final IChromosome c1 = Chromosome.createGenesInt (new MockFitnessFunction (), null, 0, 1, 5, 4, 3, 6, 2);
    final IChromosome c2 = Chromosome.createGenesInt (new MockFitnessFunction (), null, 6, 5, 0, 1, 2, 3, 4);
    final ICommonsList <IChromosome> ret = aCrossover.crossover (new CommonsArrayList <> (c1, c2));
    assertNotNull (ret);
    assertEquals (2, ret.size ());
    LOGGER.info (Arrays.toString (ret.get (0).getGeneIntArray ()));
    LOGGER.info (Arrays.toString (ret.get (1).getGeneIntArray ()));
    final int [] aNew = new int [] { 0, 1, 2, 3, 4, 5, 6 };
    assertArrayEquals (aNew, ret.get (0).getGeneIntArray ());
    assertArrayEquals (aNew, ret.get (1).getGeneIntArray ());
  }
}
