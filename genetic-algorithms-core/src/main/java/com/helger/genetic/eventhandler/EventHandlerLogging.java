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
package com.helger.genetic.eventhandler;

import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.datetime.PDTFactory;
import com.helger.genetic.model.chromosome.IChromosome;

/**
 * A special event handler that logs if a new fittest chromosome is found.
 * 
 * @author Philip Helger
 */
public class EventHandlerLogging extends EventHandlerCollecting
{
  private static final Logger LOGGER = LoggerFactory.getLogger (EventHandlerLogging.class);

  public EventHandlerLogging ()
  {
    this (null);
  }

  public EventHandlerLogging (@Nullable final IEventHandler aNestedEventHandler)
  {
    super (aNestedEventHandler);
  }

  @Override
  protected void internalOnNewFittestChromosome (@Nonnull final IChromosome aCurrentFittest)
  {
    LOGGER.info (PDTFactory.getCurrentLocalDateTime ().toString () +
                 ": New fittest [" +
                 aCurrentFittest.getFitness () +
                 "]: " +
                 Arrays.toString (aCurrentFittest.getGeneIntArray ()));
  }
}
