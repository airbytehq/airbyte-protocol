import { AirbyteMessage, AirbyteRecordMessage } from "../dist/index";

// there should be no type errors
const recordMessage: AirbyteRecordMessage = {
  stream: "foo",
  emitted_at: Date.now(),
  data: { a: 1, b: 2 },
};
const message: AirbyteMessage = { type: "RECORD", message: recordMessage };

console.log(message);
