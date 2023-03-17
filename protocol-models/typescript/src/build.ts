import * as path from "path";
import * as fs from "fs";
import { fileURLToPath } from "url";
import * as yaml from "js-yaml";
import { compile } from "json-schema-to-typescript";

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
const dest_dir = path.join(__dirname, "..", "dist");

const files = {
  "Airbyte Protocol": "airbyte_protocol",
  WellKnownTypes: "well_known_types",
};

const main = async () => {
  console.log(`---`);
  console.log(`Building Airbyte Protocol from ${src_dir}`);
  if (!fs.existsSync(dest_dir)) fs.mkdirSync(dest_dir);

  let indexFileContent = ``;

  for (const [name, filename] of Object.entries(files)) {
    indexFileContent += `export * from "./${filename}";\r\n`;

    const jsonSchema = yaml.load(
      fs.readFileSync(path.join(src_dir, filename + ".yaml")).toString()
    );
    const types = await compile(jsonSchema, name);
    const destFile = path.join(dest_dir, filename + ".ts");
    fs.writeFileSync(destFile, types);
    console.log(` * wrote ${destFile}`);
  }

  const indexFilePath = path.join(dest_dir, "index.ts");
  fs.writeFileSync(indexFilePath, indexFileContent);
  console.log(` * wrote ${indexFilePath}`);

  console.log("Done!");
};

main();
