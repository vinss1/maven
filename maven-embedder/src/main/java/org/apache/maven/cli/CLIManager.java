package org.apache.maven.cli;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author Jason van Zyl
 */
public class CLIManager
{
    public static final String ALTERNATE_POM_FILE = "f";

    public static final String BATCH_MODE = "B";

    public static final String SET_SYSTEM_PROPERTY = "D";

    public static final String OFFLINE = "o";

    public static final String QUIET = "q";

    public static final String DEBUG = "X";

    public static final String ERRORS = "e";

    public static final String HELP = "h";

    public static final String VERSION = "v";

    public static final String SHOW_VERSION = "V";

    public static final String NON_RECURSIVE = "N";

    public static final String UPDATE_SNAPSHOTS = "U";

    public static final String ACTIVATE_PROFILES = "P";

    public static final String SUPRESS_SNAPSHOT_UPDATES = "nsu";

    public static final String CHECKSUM_FAILURE_POLICY = "C";

    public static final String CHECKSUM_WARNING_POLICY = "c";

    public static final String ALTERNATE_USER_SETTINGS = "s";

    public static final String ALTERNATE_GLOBAL_SETTINGS = "gs";

    public static final String ALTERNATE_USER_TOOLCHAINS = "t";

    public static final String ALTERNATE_GLOBAL_TOOLCHAINS = "gt";

    public static final String FAIL_FAST = "ff";

    public static final String FAIL_AT_END = "fae";

    public static final String FAIL_NEVER = "fn";

    public static final String RESUME_FROM = "rf";

    public static final String PROJECT_LIST = "pl";

    public static final String ALSO_MAKE = "am";

    public static final String ALSO_MAKE_DEPENDENTS = "amd";

    public static final String LOG_FILE = "l";

    public static final String ENCRYPT_MASTER_PASSWORD = "emp";

    public static final String ENCRYPT_PASSWORD = "ep";

    public static final String THREADS = "T";

    public static final String LEGACY_LOCAL_REPOSITORY = "llr";

    public static final String BUILDER = "b";

    protected Options options;

    // CHECKSTYLE_OFF: LineLength
    public CLIManager()
    {
        options = new Options();
        options.addOption( Option.builder( HELP ).longOpt( "help" ).desc( "Display help information" ).build() );
        options.addOption( Option.builder( ALTERNATE_POM_FILE ).longOpt( "file" ).hasArg().desc( "Force the use of an alternate POM file (or directory with pom.xml)" ).build() );
        options.addOption( Option.builder( SET_SYSTEM_PROPERTY ).longOpt( "define" ).hasArgs().valueSeparator().desc( "Define a system property" ).build() );
        options.addOption( Option.builder( OFFLINE ).longOpt( "offline" ).desc( "Work offline" ).build() );
        options.addOption( Option.builder( VERSION ).longOpt( "version" ).desc( "Display version information" ).build() );
        options.addOption( Option.builder( QUIET ).longOpt( "quiet" ).desc( "Quiet output - only show errors" ).build() );
        options.addOption( Option.builder( DEBUG ).longOpt( "debug" ).desc( "Produce execution debug output" ).build() );
        options.addOption( Option.builder( ERRORS ).longOpt( "errors" ).desc( "Produce execution error messages" ).build() );
        options.addOption( Option.builder( NON_RECURSIVE ).longOpt( "non-recursive" ).desc( "Do not recurse into sub-projects" ).build() );
        options.addOption( Option.builder( UPDATE_SNAPSHOTS ).longOpt( "update-snapshots" ).desc( "Forces a check for missing releases and updated snapshots on remote repositories" ).build() );
        options.addOption( Option.builder( ACTIVATE_PROFILES ).longOpt( "activate-profiles" ).desc( "Comma-delimited list of profiles to activate" ).hasArg().build() );
        options.addOption( Option.builder( BATCH_MODE ).longOpt( "batch-mode" ).desc( "Run in non-interactive (batch) mode (disables output color)" ).build() );
        options.addOption( Option.builder( SUPRESS_SNAPSHOT_UPDATES ).longOpt( "no-snapshot-updates" ).desc( "Suppress SNAPSHOT updates" ).build() );
        options.addOption( Option.builder( CHECKSUM_FAILURE_POLICY ).longOpt( "strict-checksums" ).desc( "Fail the build if checksums don't match" ).build() );
        options.addOption( Option.builder( CHECKSUM_WARNING_POLICY ).longOpt( "lax-checksums" ).desc( "Warn if checksums don't match" ).build() );
        options.addOption( Option.builder( ALTERNATE_USER_SETTINGS ).longOpt( "settings" ).hasArg().desc( "Alternate path for the user settings file" ).build() );
        options.addOption( Option.builder( ALTERNATE_GLOBAL_SETTINGS ).longOpt( "global-settings" ).hasArg().desc( "Alternate path for the global settings file" ).build() );
        options.addOption( Option.builder( ALTERNATE_USER_TOOLCHAINS ).longOpt( "toolchains" ).hasArg().desc( "Alternate path for the user toolchains file" ).hasArg().build() );
        options.addOption( Option.builder( ALTERNATE_GLOBAL_TOOLCHAINS ).longOpt( "global-toolchains" ).desc( "Alternate path for the global toolchains file" ).hasArg().build() );
        options.addOption( Option.builder( FAIL_FAST ).longOpt( "fail-fast" ).desc( "Stop at first failure in reactorized builds" ).build() );
        options.addOption( Option.builder( FAIL_AT_END ).longOpt( "fail-at-end" ).desc( "Only fail the build afterwards; allow all non-impacted builds to continue" ).build() );
        options.addOption( Option.builder( FAIL_NEVER ).longOpt( "fail-never" ).desc( "NEVER fail the build, regardless of project result" ).build() );
        options.addOption( Option.builder( RESUME_FROM ).longOpt( "resume-from" ).hasArg().desc( "Resume reactor from specified project" ).build() );
        options.addOption( Option.builder( PROJECT_LIST ).longOpt( "projects" ).desc( "Comma-delimited list of specified reactor projects to build instead of all projects. A project can be specified by [groupId]:artifactId or by its relative path." ).hasArg().build() );
        options.addOption( Option.builder( ALSO_MAKE ).longOpt( "also-make" ).desc( "If project list is specified, also build projects required by the list" ).build() );
        options.addOption( Option.builder( ALSO_MAKE_DEPENDENTS ).longOpt( "also-make-dependents" ).desc( "If project list is specified, also build projects that depend on projects on the list" ).build() );
        options.addOption( Option.builder( LOG_FILE ).longOpt( "log-file" ).hasArg().desc( "Log file where all build output will go" ).build() );
        options.addOption( Option.builder( SHOW_VERSION ).longOpt( "show-version" ).desc( "Display version information WITHOUT stopping build" ).build() );
        options.addOption( Option.builder( ENCRYPT_MASTER_PASSWORD ).longOpt( "encrypt-master-password" ).optionalArg( true ).desc( "Encrypt master security password" ).build() );
        options.addOption( Option.builder( ENCRYPT_PASSWORD ).longOpt( "encrypt-password" ).optionalArg( true ).desc( "Encrypt server password" ).build() );
        options.addOption( Option.builder( THREADS ).longOpt( "threads" ).hasArg().desc( "Thread count, for instance 2.0C where C is core multiplied" ).build() );
        options.addOption( Option.builder( LEGACY_LOCAL_REPOSITORY ).longOpt( "legacy-local-repository" ).desc( "Use Maven 2 Legacy Local Repository behaviour, ie no use of _remote.repositories. Can also be activated by using -Dmaven.legacyLocalRepo=true" ).build() );
        options.addOption( Option.builder( BUILDER ).longOpt( "builder" ).hasArg().desc( "The id of the build strategy to use" ).build() );

        // Adding this back in for compatibility with the verifier that hard codes this option.
        options.addOption( Option.builder( "npr" ).longOpt( "no-plugin-registry" ).desc( "Ineffective, only kept for backward compatibility" ).build() );
        options.addOption( Option.builder( "cpu" ).longOpt( "check-plugin-updates" ).desc( "Ineffective, only kept for backward compatibility" ).build() );
        options.addOption( Option.builder( "up" ).longOpt( "update-plugins" ).desc( "Ineffective, only kept for backward compatibility" ).build() );
        options.addOption( Option.builder( "npu" ).longOpt( "no-plugin-updates" ).desc( "Ineffective, only kept for backward compatibility" ).build() );

    }
    // CHECKSTYLE_ON: LineLength

    public CommandLine parse( String[] args )
        throws ParseException
    {
        // We need to eat any quotes surrounding arguments...
        String[] cleanArgs = CleanArgument.cleanArgs( args );

        CommandLineParser parser = new DefaultParser();

        return parser.parse( options, cleanArgs );
    }

    public void displayHelp( PrintStream stdout )
    {
        stdout.println();

        PrintWriter pw = new PrintWriter( stdout );

        HelpFormatter formatter = new HelpFormatter();

        formatter.printHelp( pw, HelpFormatter.DEFAULT_WIDTH, "mvn [options] [<goal(s)>] [<phase(s)>]", "\nOptions:",
                             options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD, "\n", false );

        pw.flush();
    }
}
