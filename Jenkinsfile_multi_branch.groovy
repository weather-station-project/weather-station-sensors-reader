@Library('shared-library') _
import com.davidleonm.WeatherStationSensorsReaderVariables

pipeline {
  agent { label 'slave' }

  stages {
    stage('Prepare Python ENV') {
      steps {
        script {
          setBuildStatus('pending', "${WeatherStationSensorsReaderVariables.RepositoryName}")

          // Clean & Prepare new python environment
          sh 'rm -rf ENV'
          sh 'python3 -m venv ENV'

          // sh 'ENV/bin/pip install --upgrade pip'
          // sh "ENV/bin/pip install -r ${WORKSPACE}/WeatherStationSensorsReader/requirements.txt"
        }
      }
    }

    stage('Execute unit tests') {
      steps {
        script {
          sh "ENV/bin/python -m unittest discover -s ${WORKSPACE}/WeatherStationSensorsReader"
        }
      }
    }
  }
  post {
    success {
      script {
        setBuildStatus('success', "${WeatherStationSensorsReaderVariables.RepositoryName}")
      }
    }

    failure {
      script {
        setBuildStatus('failure', "${WeatherStationSensorsReaderVariables.RepositoryName}")
      }
    }
  }
}
