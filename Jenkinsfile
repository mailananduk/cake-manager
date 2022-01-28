pipeline {
    /* using docker file
     environment {
        JAVA_TOOL_OPTIONS = "-Duser.home=/home/jenkins"
    }
    agent {
        dockerfile {
            label "docker"
            args "-v /tmp/maven:/home/jenkins/.m2 -e MAVEN_CONFIG=/home/jenkins/.m2"
        }
    } */

    agent  any

    stages {
        stage("Build") {
            steps {
            //use sh for unix instead of bat
                bat "mvn -version"
                bat "mvn clean install"
            }
        }
        stage("Test") {
            steps {
                bat "mvn test"
            }
            post {
                always {
                    junit "**/targer/surefire-reports/TEST-*xml"
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
