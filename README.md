# telus_automation_qa

Posy Au-Yeung's Telus Automation QA Interview Project

Date: November 16, 2021

The purpose of this project is to create a test case with Selenium (Java) for the Telus Internet user.

Trade-Offs and Decisions:
1. Java as main programming language
   - Most proficient with using Java as a programming language
   - Language that is most used in current job
2. Selenium and JUnit5 as testing frameworks
   - Familiar with Selenium by previously working on a small project
   - JUnit5 main testing framework for current job
3. Should have added more verification for each step
   - Was not familiar with verification processes/features with Selenium
   - Would have spent more time learning verification of the UI
   - Only able to verify simple text on the web page
4. Should have made the code more robust to errors
   - Currently, the code cannot handle network errors, e.g. connection time out
   - Could make the code more robust by:
     - Adding more error handling
     - Fine-tune the timeout and retries