This is simple framework to automatw UI and API tests for xm.com
UI tests are run of three resolutions and firefox browser
To run UI test click on the play button (for Intelij users)
To run API test write in terminal: mvn test
This framework is using firefox driver, before run the test you need to specify path to it and add version here:
        System.setProperty("webdriver.gecko.driver", "/opt/homebrew/bin/geckodriver");
        WebDriverManager.firefoxdriver().driverVersion("0.34.0").setup();
