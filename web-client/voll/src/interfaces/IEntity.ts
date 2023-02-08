import IAddress from "./IAddress";

export default interface IEntity {
    address: IAddress,
    email: string,
    id: number,
    name: string,
    phone: string
}