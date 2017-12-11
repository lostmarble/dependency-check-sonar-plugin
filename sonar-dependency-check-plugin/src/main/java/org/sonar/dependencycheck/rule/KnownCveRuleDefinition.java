/*
 * Dependency-Check Plugin for SonarQube
 * Copyright (C) 2015-2017 Steve Springett
 * steve.springett@owasp.org
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
package org.sonar.dependencycheck.rule;

import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rule.Severity;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.dependencycheck.DependencyCheckPlugin;

import javax.annotation.ParametersAreNonnullByDefault;

public class KnownCveRuleDefinition implements RulesDefinition {


    @Override
    @ParametersAreNonnullByDefault
    public void define(Context context) {
        NewRepository repo = context.createRepository(DependencyCheckPlugin.REPOSITORY_KEY, DependencyCheckPlugin.LANGUAGE_KEY);
        repo.setName("OWASP");

        NewRule rule = repo.createRule(DependencyCheckPlugin.RULE_KEY);
        rule.addTags("cwe-937", "cve", "security", "vulnerability");
        rule.setName("Using Components with Known Vulnerabilities");
        rule.setSeverity(Severity.MAJOR);
        rule.setStatus(RuleStatus.READY);

        String description = "<p>Components, such as libraries, frameworks, and other software modules, " +
                "almost always run with full privileges. If a vulnerable component is exploited, such " +
                "an attack can facilitate serious data loss or server takeover. Applications using " +
                "components with known vulnerabilities may undermine application defenses and enable " +
                "a range of possible attacks and impacts.</p>" +
                "<h3>References:</h3>" +
                "<ul><li>OWASP Top 10 2013-A9: <a href=\"https://www.owasp.org/index.php/Top_10_2013-A9-Using_Components_with_Known_Vulnerabilities\">Using Components with Known Vulnerabilities</a></li>" +
                "<li><a href=\"https://cwe.mitre.org/data/definitions/937.html\">Common Weakness Enumeration CWE-937</a></li>" +
                "<p>This issue was generated by <a href=\"https://www.owasp.org/index.php/OWASP_Dependency_Check\">Dependency-Check</a>";
        rule.setHtmlDescription(description);

        // There's simply no way to know how much effort will be involved in updating/replacing a vulnerable component

        repo.done();
    }

}