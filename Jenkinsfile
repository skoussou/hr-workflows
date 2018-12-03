pipeline {

    // logrotate on builds so we don't fill up our history 
    options {
        buildDiscarder(logRotator(numToKeepStr: '3', artifactNumToKeepStr: '3'))
    }

    agent { label 'maven' }

    parameters {
        string(defaultValue: '7c32b5d2-0e25-480e-82b7-bb642a0392ed', description: 'The Jenkins Credential ID to use in order to clone the source dependencies', name: 'GIT_CREDENTIALS_ID')
    }


    stages {
        stage('Checkout Work Item Handlers') {
            steps {
                checkout([$class: 'GitSCM', 
                branches: [[name: '*/master']], 
                doGenerateSubmoduleConfigurations: false, 
                extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'work-item-handlers']],
                userRemoteConfigs: [[credentialsId: "${GIT_CREDENTIALS_ID}", url: 'ssh://git@gitlab.consulting.redhat.com:2222/uki-sa/pam-demo/work-item-handlers.git']]])
            }
        }
        stage('Build Work Item Handlers') {
            steps {
                sh 'pushd work-item-handlers;mvn clean install;popd'
            }
        }
        stage('Build Process Jar') {
            steps {
                sh "mvn clean package; mkdir -p ocp/;cp target/*.jar ocp/"
            }
           
        }
        stage('Create Kie Server Secret') {
            steps {
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
}