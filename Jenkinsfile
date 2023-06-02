pipeline {
  agent any
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  stages {
    stage('Scan') {
      steps {
	    withSonarQubeEnv(installationName: 'SonarQube-10.0') {
	  	sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184:sonar'        
      }
    }
  }
}
