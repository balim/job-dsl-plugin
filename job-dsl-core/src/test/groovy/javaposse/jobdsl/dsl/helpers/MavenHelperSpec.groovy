package javaposse.jobdsl.dsl.helpers

import javaposse.jobdsl.dsl.WithXmlAction
import javaposse.jobdsl.dsl.WithXmlActionSpec
import spock.lang.Specification

public class MavenHelperSpec extends Specification {

    List<WithXmlAction> mockActions = Mock()
    MavenHelper helper = new MavenHelper(mockActions, [type: 'maven'])
    Node root = new XmlParser().parse(new StringReader(WithXmlActionSpec.xml))

    def 'can run rootPOM'() {
        when:
        helper.rootPOM("my_module/pom.xml")

        then:
        1 * mockActions.add(_)
    }

    def 'cannot run rootPOM twice'() {
        when:
        helper.rootPOM("pom.xml")
        helper.rootPOM("my_module/pom.xml")

        then:
        thrown(IllegalStateException)
    }

    def 'cannot run rootPOM for free style jobs'() {
        setup:
        MavenHelper helper = new MavenHelper(mockActions)

        when:
        helper.rootPOM("pom.xml")

        then:
        thrown(IllegalStateException)
    }

    def 'rootPOM constructs xml'() {
        when:
        def action = helper.rootPOM("my_module/pom.xml")
        action.execute(root)

        then:
        root.rootPOM[0].value() == "my_module/pom.xml"
    }

    def 'can run goals'() {
        when:
        helper.goals("clean verify")

        then:
        1 * mockActions.add(_)
    }

    def 'cannot run goals twice'() {
        when:
        helper.goals("clean install")
        helper.goals("clean verify")

        then:
        thrown(IllegalStateException)
    }

    def 'cannot run goals for free style jobs'() {
        setup:
        MavenHelper helper = new MavenHelper(mockActions)

        when:
        helper.goals("package")

        then:
        thrown(IllegalStateException)
    }

    def 'goals constructs xml'() {
        when:
        def action = helper.goals("clean verify")
        action.execute(root)

        then:
        root.goals[0].value() == "clean verify"
    }

    def 'can run mavenOpts'() {
        when:
        helper.mavenOpts("-DskipTests")

        then:
        1 * mockActions.add(_)
    }

    def 'cannot run mavenOpts twice'() {
        when:
        helper.mavenOpts("-Xms512m")
        helper.mavenOpts("-Xmx1024m")

        then:
        thrown(IllegalStateException)
    }

    def 'cannot run mavenOpts for free style jobs'() {
        setup:
        MavenHelper helper = new MavenHelper(mockActions)

        when:
        helper.mavenOpts("-Xmx512m")

        then:
        thrown(IllegalStateException)
    }

    def 'mavenOpts constructs xml'() {
        when:
        def action = helper.mavenOpts("-DskipTests")
        action.execute(root)

        then:
        root.mavenOpts[0].value() == "-DskipTests"
    }

    def 'can run perModuleEmail'() {
        when:
        helper.perModuleEmail(false)

        then:
        1 * mockActions.add(_)
    }

    def 'cannot run perModuleEmail twice'() {
        when:
        helper.perModuleEmail(false)
        helper.perModuleEmail(true)

        then:
        thrown(IllegalStateException)
    }

    def 'cannot run perModuleEmail for free style jobs'() {
        setup:
        MavenHelper helper = new MavenHelper(mockActions)

        when:
        helper.perModuleEmail(false)

        then:
        thrown(IllegalStateException)
    }

    def 'perModuleEmail constructs xml'() {
        when:
        def action = helper.perModuleEmail(false)
        action.execute(root)

        then:
        root.perModuleEmail[0].value() == "false"
    }

    def 'can run archivingDisabled'() {
        when:
        helper.archivingDisabled(true)

        then:
        1 * mockActions.add(_)
    }

    def 'cannot run archivingDisabled twice'() {
        when:
        helper.archivingDisabled(true)
        helper.archivingDisabled(false)

        then:
        thrown(IllegalStateException)
    }

    def 'cannot run archivingDisabled for free style jobs'() {
        setup:
        MavenHelper helper = new MavenHelper(mockActions)

        when:
        helper.archivingDisabled(false)

        then:
        thrown(IllegalStateException)
    }

    def 'archivingDisabled constructs xml'() {
        when:
        def action = helper.archivingDisabled(true)
        action.execute(root)

        then:
        root.archivingDisabled[0].value() == "true"
    }

    def 'can run runHeadless'() {
        when:
        helper.runHeadless(true)

        then:
        1 * mockActions.add(_)
    }

    def 'cannot run runHeadless twice'() {
        when:
        helper.runHeadless(true)
        helper.runHeadless(false)

        then:
        thrown(IllegalStateException)
    }

    def 'cannot run runHeadless for free style jobs'() {
        setup:
        MavenHelper helper = new MavenHelper(mockActions)

        when:
        helper.runHeadless(false)

        then:
        thrown(IllegalStateException)
    }

    def 'runHeadless constructs xml'() {
        when:
        def action = helper.runHeadless(true)
        action.execute(root)

        then:
        root.runHeadless[0].value() == "true"
    }

    def 'can run ignoreUpstreamChanges'() {
        when:
        helper.ignoreUpstreamChanges(true)

        then:
        1 * mockActions.add(_)
    }

    def 'cannot run ignoreUpstreamChanges twice'() {
        when:
        helper.ignoreUpstreamChanges(true)
        helper.ignoreUpstreamChanges(false)

        then:
        thrown(IllegalStateException)
    }

    def 'cannot run ignoreUpstreamChanges for free style jobs'() {
        setup:
        MavenHelper helper = new MavenHelper(mockActions)

        when:
        helper.ignoreUpstreamChanges(false)

        then:
        thrown(IllegalStateException)
    }

    def 'ignoreUpstreamChanges constructs xml'() {
        when:
        def action = helper.ignoreUpstreamChanges(true)
        action.execute(root)

        then:
        root.ignoreUpstremChanges[0].value() == "true"
    }
}
