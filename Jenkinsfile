pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'sonar-scanner'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('DB Migration') {
            steps {
                sh '''
                docker run --rm \
                --network jenkins_network \
                -v $PWD/db/migration:/flyway/sql \
                flyway/flyway \
                -url=jdbc:postgresql://postgres:5432/sonarqube \
                -user=sonar \
                -password=sonar123 \
                migrate
                '''
            }
        }

    }
}
