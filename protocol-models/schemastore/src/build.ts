import * as path from "path";
import * as fs from "fs";
import { fileURLToPath } from "url";
import * as yaml from "js-yaml";
import dotenv from "dotenv";

const writeFilePretty = (p: string, content: any) => {
  const json = JSON.stringify(content, null, 2);
  fs.writeFileSync(p, json);
  console.log(`wrote ${p}`);
};

const ensureDir = (p: string) => {
  if (!fs.existsSync(p)) fs.mkdirSync(p);
};

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const src_dir = path.join(
  __dirname,
  "..",
  "..",
  "src",
  "main",
  "resources",
  "airbyte_protocol"
);

const env_file = path.join(__dirname, "..", "..", "..", ".env");
const { parsed: config } = dotenv.config({ path: env_file });
const version = config?.VERSION;
console.log(`writing version ${version}`);

const src_file = path.join(src_dir, "airbyte_protocol.yaml");
const json_object = yaml.load(fs.readFileSync(src_file, { encoding: "utf-8" }));

const artefact_path = path.join(__dirname, "..", "..", "..", "jsonschema");
ensureDir(artefact_path);
ensureDir(path.join(artefact_path, "latest"));
ensureDir(path.join(artefact_path, version));

writeFilePretty(
  path.join(artefact_path, "latest", "airbyte_protocol.json"),
  json_object
);

writeFilePretty(
  path.join(artefact_path, version, "airbyte_protocol.json"),
  json_object
);

process.exit(0);
