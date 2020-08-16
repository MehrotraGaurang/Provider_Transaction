pipeline {

    environment {
        registry = "gaurang98/provider_transaction"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }

    agent any

    stages {
        stage("Build Project"){
            steps {
                sh "mvn -version"
                sh "mvn clean install -DskipTests=true"
            }
        }

        stage('Cloning Git') {
              steps {
                git 'https://github.com/MehrotraGaurang/Provider_Transaction.git'
              }
            }
        stage('Building image') {
          steps{
            script {
              dockerImage = docker.build registry + ":$BUILD_NUMBER"
            }
          }
        }
        stage('Deploy Image') {
          steps{
            script {
              docker.withRegistry( '', registryCredential ) {
                dockerImage.push()
              }
            }
          }
        }
    }
}