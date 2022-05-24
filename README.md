**Product_SAP_Successfactors** is the **SAP Successfactors** repository that contain all the resources such as *source code, client resources, supporting guides, test data* and *system properties*.  These resources are created, stored, updated and utilised by **iLab (Pty) Ltd** on behalf of **SAP Successfactors**.

**iLab (Pty) Ltd**, a privately owned company, is the worldwide leader in software quality assurance. Operating from **Indiana**, **South Africa**, **Surrey**, **Australia** and **Brazil**.

# Product SAP Successfactors

**Product_SAP_Successfactors** is a privately hosted *Github Docker Repository*. These files and any _other media, content or communication published here_, are **strictly confidential** and intended solely for the use of the individual, team or entity with whom this repository is shared. If you have received access in error please notify our system manager on [System Manager](mailto:ian.joubert@ilabqa.com?subject=iLab%20Repository%20Access). If you are **not the intended** user you are notified that *disclosing, copying, distributing or taking any action* in reliance on the contents of this information is **strictly prohibited**.

## A. Project Structure
The project folder structure is laid out as follows:
1. **CLIENT** - Client centric resources such as client specific system properties etc.
2. **DEFAULT** - Application defaults such as JSON, libraries and system properties .
3. **src** - The Maven application source code.
4. **SUPPORT** - Text guides and other  system help files.
5. **TEST** - Test resources such as data, reports and Excel workbooks.

Each *main* folder ( CLIENT, DEFAULT, SUPPORT, TEST ) can contain one or more of the following folders and/or files:

1. **json** - All the necessary `.json` files that will contain information of settings such as global settings, dictionary with `xpath`'s of the elements used in the testing scripts
2. **media** - All the static resources such as images, logo's etc.
3. **properties** - All the configuration `key:value` data pairs used throughout the application and retrieved with the native **Java Property Manager**. File has the format of: `prop.<key>=<value>` where the prefix `prop` is used by the native property manager.
4. **batch** - Folder that contain `bat` (batch) files to automate some scripts and command
5. **lib** - External libraries packaged with the `JAR File` to be used in the application and/or testing scripts.
6. **data** - The test script data in different formats such as *pdf, json, xlsx*. This data drive each test's runtime and scenarios.
7. **report** - The dynamically created reports for each test run. Two types reports are generated namely
   a. PDF test reports generated by custom built functionality that use **TestNG**'s native listeners and is compiled with **PdfBox**'s document creation methods and functionality.
   b. HTML test report pages generated by the **Extent** test report package.

## B. Project Structure - Detail
### CLIENT - Client Resources
1. Captcha - Artifact from LMS core - Not required at the moment
2. Json - Artifact from LMS core - Not required at the moment
3. Logo - Client logo used in reporting
4. Properties - `client.properties` containing all `<key>:<value>` pair configuration data to configure client based configurations in the application and in the testing scripts.

### DEFAULT - Default Resources
1. Batch - Artifact from LMS core - Not required at the moment
2. Json - Artifact from LMS core - Not required at the moment
3. Lib - Third party libraries such as `iLAB_JAVA_Framework` which is used for added functionality and services
4. Media - Project media files such as diagrams of the application etc.
5. Properties - `jira.properties`, `mantis.properties`, `microfocus.properties`, `testlink.properties` and `tm4j.properties` containing all `<key>:<value>` pair configuration data to configure third party plugin configurations in the application and in the testing scripts.

### SUPPORT - Support Resources
1. `CONTACTS.md` - Markdown file containing support contact information

### TEST - Test Resources
1. Data - Excel spreadsheets, JSON files, Pdf documents with all the required test data
2. Json - `test.json` file containing json parameters and test specific data
3. Report - All the dynamically generated test reports for each test run


## C. Jar File

This is a *Maven project* that will produce a *Jar file*. This jar file can be manually compiled by running the following command: `mvn clean package` or it will be automatically generated with a ** Jenkins** build. When this repository's *master branch* is updated with a pull request (PR) or a user commits code directly on *Github*, the Jenkins build is triggered and the *Jar file* and its dependant folders are created as a Jenkins job.

The jar file consume two parameters during runtime. These parameters are `events` and `suiteId`.  The `events` parameter should be an JSON array of test scripts to be run and has the following format: `{"name":<test-name>,"id":<unique identifier>,"row":<row number>}` for example: `["{"name":"Hire","id":"Grover","row":"16"}"]`. The suiteId parameter is purely an integer representing the suite identity number.

The application that starts up by running the jar file, is a dynamic test runner that utilises the **TestNG** testing suite  to run testing scripts on a **Selenium** test grid. These testing scripts can be either run dynamically with or without the **Jenkins** integration server, or they can be run using **XML** files (that is part of the testing code base) through an IDE (code editor).

## D. Dynamic Testing - (No Jenkins)
### 1.) Local Development Environment

To run this *Java Application* directly on your *Local Environment* you have to consider if you want to use the published **Cloud Selenium** instance or a **Locally Deployed  Selenium** instance.

1.1.)  To use the **Cloud Selenium** instance you need to get the *Selenium Cloud Url* and then run the application as follows:

- Make sure you have a running *Selenium cloud instance*.
- Navigate in the `src` directory to the `main/java/com/ilabquality/modules/global/reference/SystemDefault` file and change the *url* of the cloud instance eg. `"http://54.85.200.63:4444/wd/hub"` by changing the environment variable `SELENIUM_URI`.
- Set the environment variable `USE_SELENIUM_PROXY` to false;
- Build and package the application - `mvn clean package`
- Run the following command: `java -jar target/sap_successfactors-1.0.0.jar <param1> <param2>` e.g. `java -jar target/sap_successfactors-1.0.0.jar ["{"name":"Hire","id":"Grover","row":"16"},{"name":"Rehire","id":"Grover","row":"17"}"] 3`

1.2.)  To use a **Locally Deployed Selenium** instance you need to build the **Selenium Grid** *Docker* container and then run the application as follows:

- Make sure you have a running *Docker instance*.
- Build the *Selenium Grid Container* with the *Docker Compose* command by running the following in a terminal window: `docker-compose -f  ComposeBuildSeleniumHub.yaml up`.
- Verify in your *Docker Dashboard* that the *Selenium Grid*, *Chrome Browser* and *Firefox Browser* containers are up and running.
- Navigate in the `src` directory to the `main/java/com/ilabquality/modules/global/reference/SystemDefault` file and confirm that the *url* of the local path ( environment variable `SELENIUM_URI_PROXY` )  is set to:. `"http://localhost:4444/wd/hub"`
- Set the environment variable `USE_SELENIUM_PROXY` to true;
- Build and package the application - `mvn clean package`
- Run the following command: `java -jar target/sap_successfactors-1.0.0.jar <param1> <param2>` e.g. `java -jar target/sap_successfactors-1.0.0.jar ["{"name":"Hire","id":"Grover","row":"16"},{"name":"Rehire","id":"Grover","row":"17"}"] 3`

## E. Dynamic Testing - (From Jenkins)
### 1.) Local Development Environment

To run this *Java Application* from a *Jenkins Environment* but view the test run in *Selenium* on your *Local Environment* you have to use a **Locally Deployed  Selenium** instance and then use *Ngrok Proxy Server* to give Jenkins access to your local test environment (localhost).

First you need to build the **Selenium Grid** *Docker* container, then start a **Ngrok Proxy Server**, after that, you will use the *Ngrok Proxy Uri* in this application, then publish this application to *Github*, rebuild this application in Jenkins and then finally you can run the application from *Jenkins* as follows:

- Make sure you have a running *Docker instance*.
- Build the *Selenium Grid Container* with the *Docker Compose* command by running the following in a terminal window: `docker-compose -f  ComposeBuildSeleniumHub.yaml up`.
- Verify in your *Docker Dashboard* that the *Selenium Grid*, *Chrome Browser* and *Firefox Browser* containers are up and running and healthy.
- Make sure you have *Ngrok* installed on your computer then open a second terminal window and start the *Ngrok Proxy Server* up by typing the following command: `ngrok http 4444`.
- *Ngrok* will launch an online proxy server to forward all outside traffic to the proxy, to your *Local Development Environment* called *localhost*. Example of *Ngrok Uri* is: `http://a649-41-157-248-16.ngrok.io`
- Navigate in the `src` directory to the `main/java/com/ilabquality/modules/global/reference/SystemDefault` file and replace the *url* of the local path ( environment variable `SELENIUM_URI_PROXY` )  with the **Ngrok Uri** as shown below: `"http://a649-41-157-248-16.ngrok.io/wd/hub"`
- Set the environment variable `USE_SELENIUM_PROXY` to true;
- Publish the changed codebase to the repository on *Github* and make sure the changes reflects online.
- Log into the online *Jenkins Interface* to see the two *Jenkins Jobs* called **SOURCE_CODE-SuccessFactors** and **CLIENT_TEST_RUNNER-SuccessFactors**.
- Delete both jobs *Workspace* completely then build the **SOURCE_CODE-SuccessFactors** job to generate the changed *Java Application*. Once the build is complete you will see the new *Java Jar* file in the workspace of the **CLIENT_TEST_RUNNER-SuccessFactors** job.
- This *Java Application*'s *Jar File* online points now to the *Ngrok Proxy Server* and you can test it by building the **CLIENT_TEST_RUNNER-SuccessFactors** with parameters.

## E. Viewing the Test Run
### 1.) VNC

To view a testing script running on *Selenium Grid* locally you need to use a Virtual Network Client or VNC. Download the VNC software, create a new connection and point it to `localhost:5900`. When you connect the VNC, you will connect to the realtime running of the testing script on the *Selenium Grid * in the *Docker Container*.
  