pipeline {

    // logrotate on builds so we don't fill up our history 
    options {
        buildDiscarder(logRotator(numToKeepStr: '3', artifactNumToKeepStr: '3'))
    }

    agent { label 'maven' }

    stages {

        stage('Clone and Build Work Item Handlers') {
            dir('work-item-handlers') {
                git url: 'https://gitlab.consulting.redhat.com/uki-sa/pam-demo/work-item-handlers.git'
            }

            sh 'pushd work-item-handlers;mvn clean install;popd'
        }
        stage('Build Process Jar') {
            sh "mvn clean package; mkdir -p ocp/;cp target/*.jar ${config.artifactDir}/"
        }
        stage('Create Kie Server Secret') {
            script {
                openshift.withCluster() {
                    def ksSecret = openshift.selector('secret/kieserver-app-secret')
                    def ksSecretExists = ksSecret.exists()

                    if (!ksSecretExists) {
                        sh 'keytool -genkeypair -alias jboss -keyalg RSA -keystore keystore.jks -storepass mykeystorepass --dname "CN=presales,OU=uk,O=redhat.com,L=London,S=London,C=UK"'
                        sh 'oc create secret generic kieserver-app-secret --from-file=keystore.jks'
                    }
                }
            }
        }
    }

}