export interface Device {
  inventoryId?: number;
  name: string;
  description: string;
  category: string;
  status: "AVAILABLE" | "UNAVAILABLE";
}
