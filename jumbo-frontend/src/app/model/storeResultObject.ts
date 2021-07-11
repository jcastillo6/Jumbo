import { Embedded } from "./Embedded";
import { ObjectLink } from "./objectLink";
import { Page } from "./page";

interface StoreResultObject{
    _embedded: Embedded,
    _links: ObjectLink,
    page: Page
}