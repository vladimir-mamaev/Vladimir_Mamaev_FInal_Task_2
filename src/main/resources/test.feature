Feature:
  As a user
  I want to test main site functionality
  So that I can be sure that site works correctly
  Scenario Outline:check word correctly appears in the first paragraph
    Given User opens '<homePage>' page
    When User clicks 'Русский' link
    Then User checks that the first paragraph, contains the '<keyword>' word
    Examples:
    | homePage            |keyword|
    | https://lipsum.com/ |рыба   |

Scenario Outline: check the result of setting the default text generation
  Given User opens '<homePage>' page
  When User clicks 'Generate Lorem Ipsum' button
  Then User checks that the first paragraph starts with '<subString>'
  Examples:
    | homePage            |subString                                              |
    | https://lipsum.com/ |Lorem ipsum dolor sit amet, consectetur adipiscing elit|

  Scenario Outline: check the result of generation words with valid data
    Given User opens '<homePage>' page
    When User clicks '<wordsWord>'
    And User enters '<number>' into the number field
    And User clicks 'Generate Lorem Ipsum' button
    Then User checks that amount of generation elements are <number>

    Examples:
      | homePage            |wordsWord|number|
      | https://lipsum.com/ |words    |10    |
      | https://lipsum.com/ |words    |5     |
      | https://lipsum.com/ |words    |20    |

  Scenario Outline: check the result of generation words with invalid data
    Given User opens '<homePage>' page
    When User clicks '<wordsWord>'
    And User enters '<number>' into the number field
    And User clicks 'Generate Lorem Ipsum' button
    Then User checks that with invalid data amount of generation elements are <outputNumber>
    Examples:
      | homePage            |wordsWord|number|outputNumber|
      | https://lipsum.com/ |words    |0     |5           |
      | https://lipsum.com/ |words    |-1    |5           |

  Scenario Outline: check the result of generation bytes with valid data
    Given User opens '<homePage>' page
    When User clicks '<wordsWord>'
    And User enters '<number>' into the number field
    And User clicks 'Generate Lorem Ipsum' button
    Then User checks that amount of generation bytes are <number>

    Examples:
      | homePage            |wordsWord|number|
      | https://lipsum.com/ |bytes    |10    |
      | https://lipsum.com/ |bytes    |5     |
      | https://lipsum.com/ |bytes    |20    |

  Scenario Outline: check the result of generation bytes with invalid data
    Given User opens '<homePage>' page
    When User clicks '<wordsWord>'
    And User enters '<number>' into the number field
    And User clicks 'Generate Lorem Ipsum' button
    Then User checks that with invalid data amount of generation bytes are <outputNumber>
    Examples:
      | homePage            |wordsWord|number|outputNumber|
      | https://lipsum.com/ |bytes    |0     |5           |

    Scenario Outline: verify that result no longer starts with Lorem ipsum
      Given User opens '<homePage>' page
      When User disables 'start with Lorem Ipsum' checkbox
      And User clicks 'Generate Lorem Ipsum' button
      Then User checks that the first paragraph does not start with '<subString>'
      Examples:
         | homePage            |subString  |
         | https://lipsum.com/ |Lorem ipsum|

      Scenario Outline: verify that randomly generated text paragraphs contain the word "lorem" with probability of more than 40%
        Given User opens '<homePage>' page
        When User clicks 'Generate Lorem Ipsum' button
       Then User checks the generation <number> times and gets the average number of paragraphs containing the word '<word>'
        Examples:
          | homePage            |number|word |
          | https://lipsum.com/ |10    |lorem|


