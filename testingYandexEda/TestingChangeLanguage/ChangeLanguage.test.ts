import { test, expect } from '@playwright/test';
import { LanguageChangeClass } from '../PageObjectClasses/LanguageChangeClass';
import { Languages } from './LanguagesTestData';

test('has title', async ({ page }) => {
    const languageChange = new LanguageChangeClass(page);
    let language;
    await page.goto('https://eda.yandex.ru');

    // check that language is Russian
    await languageChange.changeLanguage(Languages.Russian);
    language = await languageChange.getLanguage();
    expect(language).toEqual(Languages.Russian);
    console.log('Test "check that language is Russian", language: ', language);

    // check that language is English
    await languageChange.changeLanguage(Languages.English);
    language = await languageChange.getLanguage();
    expect(language).toEqual(Languages.English);
    console.log('Test "check that language is English", language: ', language);

    // check that language is Spanish
    await languageChange.changeLanguage(Languages.Spanish);
    language = await languageChange.getLanguage();
    expect(language).toEqual(Languages.Spanish);
    console.log('Test "check that language is Spanish", language: ', language);

    // check that language is Kyrgyz
    await languageChange.changeLanguage(Languages.Kyrgyz);
    language = await languageChange.getLanguage();
    expect(language).toEqual(Languages.Kyrgyz);
    console.log('Test "check that language is Kyrgyz", language: ', language);

    // check that language is French
    await languageChange.changeLanguage(Languages.French);
    language = await languageChange.getLanguage();
    expect(language).toEqual(Languages.French);
    console.log('Test "check that language is French", language: ', language);

    // check that language is Armenian
    await languageChange.changeLanguage(Languages.Armenian);
    language = await languageChange.getLanguage();
    expect(language).toEqual(Languages.Armenian);
    console.log('Test "check that language is Armenian", language: ', language);

    // check that language is Kazakh
    await languageChange.changeLanguage(Languages.Kazakh);
    language = await languageChange.getLanguage();
    expect(language).toEqual(Languages.Kazakh);
    console.log('Test "check that language is Kazakh", language: ', language);

    // check that language is Uzbekian (will be error)
    await page.click('[class="ChangeLanguage_languageRoot"]');
    await page.click(`//div[@class="ChangeLanguage_dropdown"] //*[contains(text(),"${Languages.Uzbekian}")]`);
    language = await languageChange.getLanguage();
    console.log(language);
    expect(language).toEqual(Languages.Uzbekian);
    console.log('Test "check that language is Uzbekian" successfull, language: ', language);
});
