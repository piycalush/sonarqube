/*
 * SonarQube
 * Copyright (C) 2009-2020 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.platform.db.migration;

import org.sonar.core.platform.Module;
import org.sonar.server.platform.db.migration.history.MigrationHistoryImpl;
import org.sonar.server.platform.db.migration.history.MigrationHistoryMeddler;
import org.sonar.server.platform.db.migration.step.MigrationStepRegistryImpl;
import org.sonar.server.platform.db.migration.step.MigrationStepsProvider;
import org.sonar.server.platform.db.migration.version.v00.DbVersion00;
import org.sonar.server.platform.db.migration.version.v80.DbVersion80;
import org.sonar.server.platform.db.migration.version.v81.DbVersion81;
import org.sonar.server.platform.db.migration.version.v82.DbVersion82;
import org.sonar.server.platform.db.migration.version.v83.DbVersion83;
import org.sonar.server.platform.db.migration.version.v84.DbVersion84;
import org.sonar.server.platform.db.migration.version.v84.util.DropPrimaryKeySqlGenerator;
import org.sonar.server.platform.db.migration.version.v84.util.SqlHelper;
import org.sonar.server.platform.db.migration.version.v85.DbVersion85;
import org.sonar.server.platform.db.migration.version.v86.DbVersion86;

public class MigrationConfigurationModule extends Module {
  @Override
  protected void configureModule() {
    add(
      // DbVersion implementations
      DbVersion00.class,
      DbVersion80.class,
      DbVersion81.class,
      DbVersion82.class,
      DbVersion83.class,
      DbVersion84.class,
      DbVersion85.class,
      DbVersion86.class,

      // migration steps
      MigrationStepRegistryImpl.class,
      new MigrationStepsProvider(),

      // history
      MigrationHistoryImpl.class,
      MigrationHistoryMeddler.class,

      // Only needed for 8.3
      SqlHelper.class,
      DropPrimaryKeySqlGenerator.class);
  }
}
