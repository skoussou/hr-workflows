Repository Init Content
=======================


## Users
Following users and groups should be set up on eap `(in standalone/configuration/application-roles.properties)`

* Bob=kie-server,interviewer
* Ann=kie-server,interviewer
* Eve=kie-server,interviewer
* Tom=kie-server,talent-acquisition
* Tina=kie-server,talent-acquisition
* Bill=kie-server,applicant
* Ava=kie-server,applicant
* adminhr=kie-server,admin,talent-acquisition,interviewer,applicant,rest-all,kiemgmt,Administrators

Script example:

```
on OS pod:

cd /opt/eap &&
./bin/add-user.sh -a -u 'Bob' -p 'Password1!' -g 'kie-server,interviewer' &&
./bin/add-user.sh -a -u 'Ann' -p 'Password1!' -g 'kie-server,interviewer' &&
./bin/add-user.sh -a -u 'Eve' -p 'Password1!' -g 'kie-server,interviewer' &&  
./bin/add-user.sh -a -u 'Tom' -p 'Password1!' -g 'kie-server,talent-aquisition' &&
./bin/add-user.sh -a -u 'Tina' -p 'Password1!' -g 'kie-server,talent-aquisition' &&
./bin/add-user.sh -a -u 'Bill' -p 'Password1!' -g 'kie-server,applicant' &&
./bin/add-user.sh -a -u 'Ava' -p 'Password1!' -g 'kie-server,applicant' &&
./bin/add-user.sh -a -u 'adminhr' -p 'Password1!' -g 'kie-server,admin,talent-aquisition,interviewer,applicant,rest-all,kiemgmt,Administrators'
```

## Access Front End on OS
Developed and tested on Chrome so recommend Chrome for demo

CORS not setup so need to run Chrome without security

Steps:

1. make sure you can access backend: 
    * navigate to `http://myapp-kieserver-rh-hr-demo.apps.testcoe3.appdevcoe.opentlc.com/`  You should just get `FORBIDDEN` in the browser.  If you get `Your connection is not private`, click on `ADVANCED` and `Proceed to ...` until you get `FORBIDDEN` page

2. run Chrome with security disabled

    *  On Mac: quit any running instances of Chrome, then run `open -a "Google Chrome" --args --disable-web-security --user-data-dir` from a Terminal
  
   * Or, install CORS extension and switch on/off in running Chrome `https://chrome.google.com/webstore/detail/allow-control-allow-origi/nlfbmbojpeacfghkpbjhddihlkkiljbi`

3. Navigate to front end at `http://hr-demo-ui-rh-hr-demo.apps.testcoe3.appdevcoe.opentlc.com/pam-hr-demo-ui`


# Instructions for Running locally:
* install EAP7
* install PAM7
* install maven (3.5.3 used for dev)

### PAM process
* The BPM version being used is `forum_demo` branch from

`ssh://git@gitlab.consulting.redhat.com:2222/barclays/barclays-hr-bc-workflows.git`

* Make sure you use this, it's the only one that'll run end to end
* Run `mvn clean install` to create kjar
* Run command below to create EAP users
* start EAP using `<EAP folder>/bin/standalone.sh -c standalone-full.xml`

### WIH code
* master branch on  

`ssh://git@gitlab.consulting.redhat.com:2222/pbrown/rhdemo-hr-bc-workflows.git`

* Run `mvn clean install`

### Command to create users on EAP is:
```
./bin/add-user.sh -a -u 'Bob' -p 'Password1!' -g 'kie-server,interviewer' &&
./bin/add-user.sh -a -u 'Ann' -p 'Password1!' -g 'kie-server,interviewer' &&
./bin/add-user.sh -a -u 'Eve' -p 'Password1!' -g 'kie-server,interviewer' && 
./bin/add-user.sh -a -u 'Tom' -p 'Password1!' -g 'kie-server,talent-aquisition' &&
./bin/add-user.sh -a -u 'Tina' -p 'Password1!' -g 'kie-server,talent-aquisition' &&
./bin/add-user.sh -a -u 'Bill' -p 'Password1!' -g 'kie-server,applicant' &&
./bin/add-user.sh -a -u 'Ava' -p 'Password1!' -g 'kie-server,applicant' &&
./bin/add-user.sh -a -u 'adminhr' -p 'Password1!' -g 'kie-server,admin,talent-aquisition,interviewer,applicant,rest-all,kiemgmt,Administrators'
```

### Create Container
Curl to create container is:
```
curl -X PUT \
  http://localhost:8080/kie-server/services/rest/server/containers/onboarding_1.0.0-SNAPSHOT \
  -H 'Authorization: Basic YnBtc0FkbWluOnJlZGhhdDEh' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/xml' \
  -H 'Postman-Token: 0f94fee5-d017-4758-9334-9283d52510d6' \
  -d '<kie-container container-id="onboarding_1.0.0-SNAPSHOT">
  <release-id>
    <artifact-id>onboarding</artifact-id>
    <group-id>com.rhdemo.hr</group-id>
    <version>1.0.0-SNAPSHOT</version>
  </release-id>
</kie-container>'
```
### Front End

* The front end is in new mavenised repo ssh://git@gitlab.consulting.redhat.com:2222/barclays/client-app-maven.git although you don't use maven to run locally, just switch to app folder and run as per instrutions below
* master branch
* there's some details in README.md file which may help

* node 6.14.3 to be sure although 6 or greater probably OK

* To run locally:

  1. change to /src/main/hr-demo-webapp foldet
  2. run npm install
  3. then run ionic serve
  4. Front end should start up at http://localhost:8100 automatically
  5. (may also need to specifically install Ionic, not sure - npm install -g cordova ionic if you do, maybe sudo too, can't remember)
  6. login to app
  7. In settings change Host to http://localhost:8080
  8. In settings change Server Path to /kie-server/services/rest/server
  9. should be good to go

# Run a Case from Front End
Videos:
First one shows switching logins to different user roles, second just runs everything as user adminhr which is probably easiest.  Only issue with adminhr is you have to release some tasks that are assigned to specific users before you can run them as adminhr.

For the Schedule Interview task you need to enter at least one name with Interview Role - Eve, Ann or Bob.  It'll generate and interview task for each name entered

https://drive.google.com/open?id=1qARv485auEKYy_OXYEaXY8UZiBDwDGNf

https://drive.google.com/open?id=1hcCmbIURzSLzuar0-8NwugAucoJ8m1tK

Some more instructions for running by switching users:

* Login as applicant Bill
* Create case/application
* Login as TA Tom or Tina to run Approve and Schedule Interview Tasks
* Login as Eve, Ann or Bob to run Interview Tasks
* Login as applicant Bill to run Accept/Reject offer task
* You can change users/roles from the Settings rather than logout/login

# Openshift Credentials

Openshift instance
`https://master.testcoe3.appdevcoe.opentlc.com/`

login: `user1 / r3dh4t1!`

project used for Fourum is `RH HR Demo`

## Build / Deploy
* push updated code to `forum_demo` branch on `ssh://git@gitlab.consulting.redhat.com:2222/barclays/barclays-hr-bc-workflows.git`
* start OS buid
* when new Pods are created and running, login to each pod and run the create users command/script above 
