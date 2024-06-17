import { Page } from "@playwright/test";

export class LanguageChangeClass {
    private readonly changeLanguageSelector = '[class="ChangeLanguage_languageRoot"]';
    private readonly getLanguageSelector = '[class="ChangeLanguage_name"]';
    private setLanguageSelector = (value: string) => `//button[@class="ChangeLanguage_item"][text()="${value}"]`;
    
    constructor(public page: Page){ }

    public async changeLanguage(value: string) {
        await this.page.click(this.changeLanguageSelector);
        await this.page.click(this.setLanguageSelector(value));
        // await this.page.waitForTimeout(5000);
    }

    public async getLanguage() {
        return await this.page.textContent(this.getLanguageSelector);
    }
}


