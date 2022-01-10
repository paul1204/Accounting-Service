
pipeline {
    agent any

    environment{
        NEW_VERISON = '1.1.0'
   //     SERVER_CREDENTIALS = credentials('')
        //Need Credentials Binding PLugin
        //
    }

    tools{
        maven 'Maven'
    }

    parameters{
        //Select which version you want to build
        string(name: 'VERSION', defaultValue: '', description: 'version to deply on prod')
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
        booleanParam(name: 'executeTests', defaultValue: false, description: '')
    }


    stages {

        stage("build") {
           // when{
            //    expression{
             //   BRANCH_NAME = 'main' || CODE_CHANGES == true
              //  }
           // }
            steps {
                    echo 'building the application'
                    echo "building version ${NEW_VERISON}"
                    //sh "mvn install" Maven Install
            }
        }
        stage("test") {
            //Run test on a certain branch
            when{
                expression{
                    params.executeTests
                }
            }
            steps {
                    echo 'testing the application'
            }
            }

        stage("deploy") {
                     steps {
                            echo 'deploying the application'
                         //   echo "deploying with ${SERVER_CREDENTIALS}"
                          //  sh "${SERVER_CREDENTIALS}"
                            //or
                          //  withCredentials([usernamePassword(credentials: 'ID of the Projec' , usernameVariable: USER, passwordVariable:PWD )]){
                            //    sh "some shell command ${USER} ${PWD}"
                          //  }

                          //  echo "deploying version ${params.VERSION}"
                            }
            }
    }

   // post{
    //always{}
   // success{}
   // failure{}
   // }

}