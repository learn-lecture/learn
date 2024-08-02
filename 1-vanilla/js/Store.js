import { createNextId, createPastDate } from "./helpers.js";
import { TabType } from "./views/TabView.js";

const tag = "[store]";

export default class Store {
  constructor(storage) {
    if (!storage) throw "no storage";

    this.storage = storage;
    this.searchResult = [];
    this.searchKeyword = "";
    this.selectedTab = TabType.KEYWORD;
  }

  search(keyword) {
    this.searchKeyword = keyword;
    this.searchResult = this.storage.productData.filter((product) => 
      product.name.includes(keyword)
    );
    this.addHistory(keyword);
  }

  addHistory(keyword) {
    const alreadySearch = this.storage.historyData.some((history) => history.keyword === keyword);
    if (alreadySearch) {
      this.removeHistory(keyword);
    }
    const id = createNextId(this.storage.historyData);
    const date = new Date();
    this.storage.historyData.push({id, keyword, date});
  }

  reset() {
    this.searchKeyword = "";
  }

  select(tab) {
    this.selectedTab = tab;
  }

  getKeywordList() {
    return this.storage.keywordData;
  }

  getHistoryList() {
    return this.storage.historyData.sort(this._sortHistory);
  }

  _sortHistory(history1, history2) {
    return history2.date - history1.date;
  }

  removeHistory(keyword) {
    this.storage.historyData = this.storage.historyData.filter(
      (history) => history.keyword !== keyword
    );
  }

}
